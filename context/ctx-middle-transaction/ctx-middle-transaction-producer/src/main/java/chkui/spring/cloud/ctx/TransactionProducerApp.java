package chkui.spring.cloud.ctx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import chkui.spring.cloud.ctx.middle.transction.config.TransactionProducerConfiguration;
import chkui.spring.cloud.ctx.order.context.OrderContext;
import chkui.spring.cloud.ctx.order.interfaces.OrderInterface;
import chkui.spring.cloud.ctx.transaction.interfaces.TransactionInterface;
import chkui.spring.cloud.ctx.user.context.UserContext;
import chkui.spring.cloud.ctx.user.interfaces.UserInterface;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class TransactionProducerApp {
	Logger logger = LoggerFactory.getLogger(TransactionProducerApp.class);
	
    public static void main(String[] args) {
		SpringApplication.run(TransactionProducerApp.class, args);
    }
}

@Configuration
@RibbonClient(name = "custom", configuration = TransactionProducerConfiguration.class)
class InnerConfiguration{
}

@RestController
@RequestMapping(value = "/api/pay")
class TransactionController {
	
	@Autowired
	private UserInterface userInterface;
	
	@Autowired
	private OrderInterface orderInterface;
	
	@Autowired
	private TransactionInterface transactionInterface;

	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	@RequestMapping(value = "/getUser/{snCode}",method = RequestMethod.GET)
    public UserContext getOrderInfo(@PathVariable("snCode")String snCode) {
		UserContext user = userInterface.getUserBySnCode(snCode);
        return user;
    }
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/order/test/set/{orderKey}",method = RequestMethod.GET)
    public OrderContext testSetOrder(@PathVariable("orderKey")String orderKey) {
        return orderInterface.setOrderState(orderKey, OrderContext.OrderState.PrePay);
    }
	
	@RequestMapping(value = "/order/dual/{orderKey}",method = RequestMethod.GET)
    public OrderContext getOrder(@PathVariable("orderKey")String orderKey) {
		//1. 獲取訂單，根據訂單進行預處理
		OrderContext orderCtx = orderInterface.getOrder(orderKey);
		
		//2. 喚起支付過程
		int result = transactionInterface.transaction(orderKey);
		
		//3. 支付完成後發起後置異步處理
		// 分潤、異步通知等等
		
		//4. 更新訂單狀態
		if(1 == result) {
			orderInterface.setOrderState(orderKey, OrderContext.OrderState.Release);
		}
		
		//5. 返回
        return orderCtx;
    }
}
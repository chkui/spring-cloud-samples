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

import chkui.spring.cloud.ctx.middle.order.config.ProducerConfiguration;
import chkui.spring.cloud.ctx.order.dto.OrderDto;
import chkui.spring.cloud.ctx.order.entity.OrderPayPip;
import chkui.spring.cloud.ctx.order.entity.OrderUser;
import chkui.spring.cloud.ctx.order.interfaces.OrderInterface;
import chkui.spring.cloud.ctx.user.context.UserContext;
import chkui.spring.cloud.ctx.user.entity.PayPip;
import chkui.spring.cloud.ctx.user.entity.User;
import chkui.spring.cloud.ctx.user.interfaces.UserInterface;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class OrderProducerApp {
	Logger logger = LoggerFactory.getLogger(OrderProducerApp.class);
	
    public static void main(String[] args) {
		SpringApplication.run(OrderProducerApp.class, args);
    }
}

@Configuration
@RibbonClient(name = "custom", configuration = ProducerConfiguration.class)
class InnerConfiguration{
}

@RestController
@RequestMapping(value = "/api/order")
class OrderProducerController {
	Logger logger = LoggerFactory.getLogger(OrderProducerController.class);
	
	@Autowired
	private UserInterface userInterface;
	
	@Autowired
	private OrderInterface orderInterface;

	@GetMapping("/test")
	public String test() {
		return userInterface.test("123456");
	}
	
	@RequestMapping(value = "/register/{snCode}",method = RequestMethod.GET)
    public String registerOrder(@PathVariable("snCode")String snCode) {
		UserContext userCtx = userInterface.getUserBySnCode(snCode);
		User user = userCtx.getUser();
		PayPip payPip = userCtx.getPayPip();
		
		//OrderUser orderUser = new OrderUser(user.getId(), OrderUser.UserType.valueOf(user.getType().name()), user.getName());
		//OrderPayPip orderPayPip = new OrderPayPip(OrderPayPip.PipType.valueOf(payPip.getPipType().name()), payPip.getPipCode());
		
		//OrderDto orderDto = new OrderDto();
		//orderDto.setOrderUser(orderUser);
		//orderDto.setOrderPayPip(orderPayPip);
		
		logger.info("對業務數據進行轉換!");
		
        return orderInterface.addOrder(new OrderDto());
    }
}
package chkui.spring.cloud.ctx;

import java.io.IOException;
import java.net.ServerSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import chkui.spring.cloud.ctx.order.context.OrderContext;
import chkui.spring.cloud.ctx.order.interfaces.OrderInterface;
import chkui.spring.cloud.ctx.transction.config.TransactionConfiguration;
import chkui.spring.cloud.ctx.upstream.interfaces.UpstreamInterface;


@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class TransactionConsumerApplication{
	private static Logger logger = LoggerFactory.getLogger(TransactionConsumerApplication.class);
	
    public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(0);
			int port = serverSocket.getLocalPort();
	    	System.setProperty("server.port", String.valueOf(port));
	    	serverSocket.close();
			SpringApplication.run(TransactionConsumerApplication.class, args);
		} catch (IOException e) {
			logger.error("获取IP地址冲突!", e);
		}
    }
}

@Configuration
@RibbonClient(name = "custom", configuration = TransactionConfiguration.class)
class InnerConfiguration{
}

@RestController
@RequestMapping(value = "/api/transaction")
class ConsumerController {
	private static Logger logger = LoggerFactory.getLogger(ConsumerController.class);
	
	@Autowired
	DiscoveryClient client;
	
	@Autowired
	OrderInterface orderInterface;
	
	@Autowired
	UpstreamInterface upstreamInterface;
	
	@RequestMapping(value = "/dual/{orderKey}",method = RequestMethod.GET)
	//@Transactional 使用事物
    public int transaction(@PathVariable("orderKey")String orderKey) {
		//1. 獲取訂單，根據訂單進行後續處理
		OrderContext orderCtx = orderInterface.getOrder(orderKey);
		System.out.println(orderKey);
		
		//2. 根據訂單信息調用上游渠道完成支付 
		int result = upstreamInterface.upstream(orderKey);
		
		//3. 寫入自身動賬數據  insert ER-DB //TODO
		
		//4. 更新訂單信息
		if(result ==1) {
			orderInterface.setOrderState(orderKey, OrderContext.OrderState.CompletePay);
		}else {
			
		}
        return result;
    }
}
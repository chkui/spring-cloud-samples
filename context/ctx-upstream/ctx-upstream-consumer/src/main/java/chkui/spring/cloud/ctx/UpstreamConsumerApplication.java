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
import chkui.spring.cloud.ctx.upstream.config.UpstreamConfiguration;


@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class UpstreamConsumerApplication{
	private static Logger logger = LoggerFactory.getLogger(UpstreamConsumerApplication.class);
	
    public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(0);
			int port = serverSocket.getLocalPort();
	    	System.setProperty("server.port", String.valueOf(port));
	    	serverSocket.close();
			SpringApplication.run(UpstreamConsumerApplication.class, args);
		} catch (IOException e) {
			logger.error("获取IP地址冲突!", e);
		}
    }
}

@Configuration
@RibbonClient(name = "custom", configuration = UpstreamConfiguration.class)
class InnerConfiguration{
}

@RestController
@RequestMapping(value = "/api/upstream")
class ConsumerController {
	private static Logger logger = LoggerFactory.getLogger(ConsumerController.class);
	
	@Autowired
	DiscoveryClient client;
	
	@Autowired
	OrderInterface orderInterface;
	
	@RequestMapping(value = "/dual/{orderKey}",method = RequestMethod.GET)
	//@Transactional 使用事物
    public int upstream(@PathVariable("orderKey")String orderKey) {
		//1. 獲取訂單，根據訂單進行後續處理
		OrderContext orderCtx = orderInterface.getOrder(orderKey);
		logger.info("Order", orderCtx);
		
		//2. 根據訂單信息調用上游渠道
		
		//3. 返回
        return 1;
    }
}
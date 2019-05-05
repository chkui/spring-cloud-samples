package chkui.spring.cloud.evolution.consumer;

import java.io.IOException;
import java.net.ServerSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import chkui.spring.cloud.evolution.entity.OrderBase;

@EnableEurekaClient
@SpringBootApplication
public class ConsumerWebApplication{
	private static Logger logger = LoggerFactory.getLogger(ConsumerWebApplication.class);
	
    public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(0);
			int port = serverSocket.getLocalPort();
	    	System.setProperty("server.port", String.valueOf(port));
	    	serverSocket.close();
			SpringApplication.run(ConsumerWebApplication.class, args);
		} catch (IOException e) {
			logger.error("获取IP地址冲突!", e);
		}
    }
}

@RestController
class ConsumerController {

	@Autowired
	DiscoveryClient client;
	
	@RequestMapping(value = "/transaction/getOrder/{id}",method = RequestMethod.GET)
    public OrderBase getPort(@PathVariable("id")Long id ) {

		ServiceInstance localInstance = client.getLocalServiceInstance();
		
		OrderBase order = new OrderBase();
		order.setId(id);
		order.setMsg("It's a test Order");
		order.setPort(localInstance.getPort());
        return order;
    }
}
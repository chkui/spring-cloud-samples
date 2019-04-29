package chkui.spring.cloud.netflix.webclient;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;

@EnableEurekaClient
@RestController
@SpringBootApplication
public class ConsumerWebApplication{
	private static Logger logger = LoggerFactory.getLogger(ConsumerWebApplication.class);
	
	@Autowired
	private EurekaClient eurkaClient;
	
	@Autowired
	DiscoveryClient client;
	
	@RequestMapping(value = "/knownService",method = RequestMethod.GET)
    public Set<String> home() {
		Set<String> s = eurkaClient.getAllKnownRegions();
        return s;
    }

	@RequestMapping(value = "/port",method = RequestMethod.GET)
    public int getPort() {
		ServiceInstance localInstance = client.getLocalServiceInstance();
        return localInstance.getPort();
    }
	
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
package chkui.spring.cloud.netflix.webclient;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;


@EnableEurekaClient
@RestController
@SpringBootApplication
public class ConsumerWebApplication implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {
	private static Logger logger = LoggerFactory.getLogger(ConsumerWebApplication.class);
	
	@Autowired
	private EurekaClient eurkaClient;

	private static EmbeddedServletContainerInitializedEvent event;
	
	@RequestMapping(value = "/knownService",method = RequestMethod.GET)
    public Set<String> home() {
		Set<String> s = eurkaClient.getAllKnownRegions();
        return s;
    }

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
    	ConsumerWebApplication.event = event;
    }

	@RequestMapping(value = "/port",method = RequestMethod.GET)
    public static int getPort() {
        int port = event.getEmbeddedServletContainer().getPort();
        return port;
    }
	
    public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(0); //自动探索一个端口
			int port = serverSocket.getLocalPort();
	    	System.setProperty("server.port", String.valueOf(port));
	    	serverSocket.close();
			SpringApplication.run(ConsumerWebApplication.class, args);
		} catch (IOException e) {
			logger.error("服务启动异常，获取端口错误!", e);
		}
    }
}
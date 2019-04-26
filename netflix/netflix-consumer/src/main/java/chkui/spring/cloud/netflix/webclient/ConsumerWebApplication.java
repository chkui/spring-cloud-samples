package chkui.spring.cloud.netflix.webclient;

import java.util.Set;

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
	
	@Autowired
	private EurekaClient eurkaClient;
	
	@RequestMapping(value = "/knownService",method = RequestMethod.GET)
    public Set<String> home() {
		Set<String> s = eurkaClient.getAllKnownRegions();
        return s;
    }

	private static EmbeddedServletContainerInitializedEvent event;

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
		SpringApplication.run(ConsumerWebApplication.class, args);
    }
}
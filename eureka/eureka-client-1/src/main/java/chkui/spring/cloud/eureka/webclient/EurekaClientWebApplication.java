package chkui.spring.cloud.eureka.webclient;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;

@EnableEurekaClient
@RestController
@SpringBootApplication
public class EurekaClientWebApplication {
	
	@Autowired
	private EurekaClient eurkaClient;
	
	@RequestMapping(value = "/knownService",method = RequestMethod.GET)
    public Set<String> home() {
		Set<String> s = eurkaClient.getAllKnownRegions();
        return s;
    }

    public static void main(String[] args) {
		SpringApplication.run(EurekaClientWebApplication.class, args);
    }
}

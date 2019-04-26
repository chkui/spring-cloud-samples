package chkui.spring.cloud.netflix.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import chkui.spring.cloud.netflix.configuration.ProducerConfiguration;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class ProducerApplication {
	
	@Autowired
    @LoadBalanced
    RestTemplate restTemplate;
	
    public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
    }

	@RequestMapping(value = "/port",method = RequestMethod.GET)
    public String getPort() {
        return restTemplate.getForObject("http://eureka-consumer/port", String.class);
    }
}



@Configuration
@RibbonClient(name = "custom", configuration = ProducerConfiguration.class)
class InnerConfiguration{}
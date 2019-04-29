package chkui.spring.cloud.netflix.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import chkui.spring.cloud.netflix.configuration.ProducerConfiguration;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class ProducerApplication {
	Logger logger = LoggerFactory.getLogger(ProducerApplication.class);
	
    public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
    }
}

@Configuration
@RibbonClient(name = "custom", configuration = ProducerConfiguration.class)
class InnerConfiguration{

	/**
	 * 实例化ribbon使用的RestTemplate
	 * 
	 * @return
	 */
	@Bean
	@LoadBalanced
	public RestTemplate rebbionRestTemplate() {
		return new RestTemplate();
	}
}

@RestController
class ProducerController {
	
	@Autowired
    RestTemplate restTemplate;
	
	@Autowired
	Consumer consumer;

	@RequestMapping(value = "/port1",method = RequestMethod.GET)
    public String getPortTemplate() {
        return restTemplate.getForObject("http://eureka-consumer/port", String.class);
    }

	@RequestMapping(value = "/port2",method = RequestMethod.GET)
    public String getPortFeign() {
        return consumer.getPort();
    }
}

@FeignClient("eureka-consumer")
interface Consumer {
	@RequestMapping(value = "/port", method = RequestMethod.GET)
	String getPort();
}
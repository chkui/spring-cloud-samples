package chkui.spring.cloud.evolution;

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

import chkui.spring.cloud.evolution.configuration.ProducerConfiguration;
import chkui.spring.cloud.evolution.entity.OrderBase;
import chkui.spring.cloud.evolution.interfaces.OrderInterface;

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
}

@RestController
class ProducerController {
	
	@Autowired
	private OrderInterface orderInferface;

	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	@RequestMapping(value = "/order/{id}",method = RequestMethod.GET)
    public OrderBase getOrderInfo(@PathVariable("id")Long id) {
        return orderInferface.getOrder(id);
    }
}
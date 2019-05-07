package chkui.spring.cloud.ctx;

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

import chkui.spring.cloud.ctx.middle.transction.config.TransactionProducerConfiguration;
import chkui.spring.cloud.ctx.user.context.UserContext;
import chkui.spring.cloud.ctx.user.interfaces.UserInterface;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class TransactionProducerApp {
	Logger logger = LoggerFactory.getLogger(TransactionProducerApp.class);
	
    public static void main(String[] args) {
		SpringApplication.run(TransactionProducerApp.class, args);
    }
}

@Configuration
@RibbonClient(name = "custom", configuration = TransactionProducerConfiguration.class)
class InnerConfiguration{
}

@RestController
@RequestMapping(value = "/api/pay/")
class TransactionController {
	
	@Autowired
	private UserInterface userInterface;

	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	@RequestMapping(value = "/scan-code/{snCode}",method = RequestMethod.GET)
    public UserContext getOrderInfo(@PathVariable("snCode")String snCode) {
		UserContext user = userInterface.getUserBySnCode(snCode);
        return user;
    }
}
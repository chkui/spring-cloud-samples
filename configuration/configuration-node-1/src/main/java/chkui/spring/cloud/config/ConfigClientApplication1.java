package chkui.spring.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@SpringBootApplication
public class ConfigClientApplication1 {
	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication1.class, args);
    }
}

@RestController //常规controller注解 RestFull格式
class MessageRestController {

    @Value("${message:Configuration Server Error(Node-1)}")
    private String message;

    @RequestMapping("/message")
    Mono<String> getMessage() {
        return Mono.just(this.message);
    }
}
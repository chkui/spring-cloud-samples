package chkui.spring.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ConfigClientApplication2 {
	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication2.class, args);
    }
}

/**
 * 测试服务
 * RefreshScope注解是一个隶属于Spring Cloud Context的配置，
 * 	他在org.springframework.cloud.context.config.annotation包中。
 * 	他的作用是标记 MessageRestController提供refresh接口，让整个容器重新启用新配置。
 *  refresh会调用
 * @author chenkui
 * 
 */
@RefreshScope 
@RestController //常规controller注解 RestFull格式
class MessageRestController {
    @Value("${message:Configuration Server Error(Node-2)}")
    private String message;

    @RequestMapping("/message")
    String getMessage() {
        return this.message;
    }
}
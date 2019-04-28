package chkui.spring.cloud.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 
 * @author chenkui
 * gradle升到到5.x版本后悔出现wrapper任务重复的问题。和spring cloud提供的官方配置有关。
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(EurekaServerApplication.class, args);
	}
}

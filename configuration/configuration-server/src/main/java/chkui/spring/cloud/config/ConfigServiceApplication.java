package chkui.spring.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * spring cloud config 案例。
 * 配置可以放置在git仓库上，也可以在本地或者其他任何位置。
 * 提供Config功能的过程：
 * 1）首先需要 spring-cloud-config-server提供server服务。
 * 2）其次需要 spring-web和spring-web-mvc提供web服务用于网络通信。
 * 
 * 配置：
 * application.[properties|yml]用于配置提供配置服务的端口和配置文件地址。
 * @author chenkui
 *
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServiceApplication {
	public static void main(String[] args) {
        SpringApplication.run(ConfigServiceApplication.class, args);
	}
}

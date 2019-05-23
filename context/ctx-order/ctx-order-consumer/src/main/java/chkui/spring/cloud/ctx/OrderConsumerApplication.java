package chkui.spring.cloud.ctx;

import java.io.IOException;
import java.net.ServerSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class OrderConsumerApplication {
	private static Logger logger = LoggerFactory.getLogger(OrderConsumerApplication.class);

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(0);
			int port = serverSocket.getLocalPort();
			serverSocket.close();
			System.setProperty("server.port", String.valueOf(port));
			SpringApplication.run(OrderConsumerApplication.class, args);
		} catch (IOException e) {
			logger.error("获取IP地址冲突!", e);
		}
	}
}
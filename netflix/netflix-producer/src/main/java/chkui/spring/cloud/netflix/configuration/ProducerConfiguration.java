package chkui.spring.cloud.netflix.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
public class ProducerConfiguration {

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

	/**
	 * 配置随机负载策略，需要配置属性service-B.ribbon.NFLoadBalancerRuleClassName=com.netflix.loadbalancer.RandomRule
	 */
	@Bean
	public IRule ribbonRule() {
		return new RandomRule();
	}
}

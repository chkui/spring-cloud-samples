package chkui.spring.cloud.ctx.middle.transction.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
public class TransactionProducerConfiguration {
	Logger logger = LoggerFactory.getLogger(TransactionProducerConfiguration.class);

	/**
	 * 配置随机负载策略，需要配置属性service-B.ribbon.NFLoadBalancerRuleClassName=com.netflix.loadbalancer.RandomRule
	 */
	@Bean
	public IRule ribbonRule() {
		return new RandomRule();
	}
}

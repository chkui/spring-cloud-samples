package chkui.spring.cloud.evolution.interfaces;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import chkui.spring.cloud.evolution.entity.OrderBase;

@FeignClient("eureka-consumer")
public interface OrderInterface {
	@GetMapping("/transaction/getOrder/{id}")
	OrderBase getOrder(@PathVariable("id")Long id);
}

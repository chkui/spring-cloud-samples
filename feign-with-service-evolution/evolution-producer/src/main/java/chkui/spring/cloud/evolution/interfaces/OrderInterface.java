package chkui.spring.cloud.evolution.interfaces;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import chkui.spring.cloud.evolution.entity.OrderBase;

@FeignClient("eureka-consumer")
public interface OrderInterface {
	@RequestMapping(value = "/transaction/getOrder/{id}",method = RequestMethod.GET)
	OrderBase getOrder(@PathVariable("id")Long id);
}

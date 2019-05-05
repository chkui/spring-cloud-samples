package chkui.spring.cloud.evolution.interfaces;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import chkui.spring.cloud.evolution.entity.OrderBase;

/**
 * 订单相关的功能接口
 * @author 陈葵
 * 
 */
@FeignClient("eureka-consumer")
public interface OrderInterface {
	/**
	 * 获取订单信息
	 * @param id 订单id
	 * @return
	 */
	@GetMapping("/transaction/getOrder/{id}")
	OrderBase getOrder(@PathVariable("id")Long id);
}

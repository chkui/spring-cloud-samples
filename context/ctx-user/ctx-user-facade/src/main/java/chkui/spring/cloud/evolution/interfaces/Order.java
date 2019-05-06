package chkui.spring.cloud.evolution.interfaces;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import chkui.spring.cloud.evolution.entity.OrderBase;

/**
 * 订单先关的服务
 * @author chkui
 * 
 */
@FeignClient("evolution-consumer")
public interface Order {
	/**
	 * 订单id
	 * @param id 要获取的订单对应的id
	 * @return
	 */
	@RequestMapping(value = "/transaction/getOrder/{id}",method = RequestMethod.GET)
	OrderBase getOrder(@PathVariable("id")Long id);
}

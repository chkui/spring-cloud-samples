package chkui.spring.cloud.ctx.order.interfaces;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import chkui.spring.cloud.ctx.order.context.OrderContext;
import chkui.spring.cloud.ctx.order.dto.OrderDto;

/**
 * 
 * @author chkui
 * 
 */
@FeignClient("order-consumer")
@RequestMapping(value = "/api/order")
public interface OrderInterface {
	/**
	 * 
	 * @param id 
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	String addOrder(@RequestBody OrderDto orderDto);

	@RequestMapping(value = "/set/{key}/{state}",method = RequestMethod.POST)
	OrderContext setOrderState(@PathVariable("key")String key, @PathVariable("state") OrderContext.OrderState state);
	
	@RequestMapping(value = "/get/{key}",method = RequestMethod.GET)
	OrderContext getOrder(@PathVariable("key") String key);
}

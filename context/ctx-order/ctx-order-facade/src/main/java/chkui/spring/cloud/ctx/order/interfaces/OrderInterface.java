package chkui.spring.cloud.ctx.order.interfaces;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import chkui.spring.cloud.ctx.order.context.OrderContext;
import chkui.spring.cloud.ctx.order.dto.OrderDto;

/**
 * 订单先关的服务
 * @author chkui
 * 
 */
@FeignClient("order-consumer")
public interface OrderInterface {
	/**
	 * 订单id
	 * @param id 要获取的订单对应的id
	 * @return
	 */
	@RequestMapping(value = "/api/order/addOrder",method = RequestMethod.POST)
	OrderContext addOrder(@RequestBody OrderDto orderDto);
}

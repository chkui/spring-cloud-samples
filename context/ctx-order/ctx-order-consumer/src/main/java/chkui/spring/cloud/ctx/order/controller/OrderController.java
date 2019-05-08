package chkui.spring.cloud.ctx.order.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import chkui.spring.cloud.ctx.order.context.OrderContext;
import chkui.spring.cloud.ctx.order.dto.OrderDto;
import chkui.spring.cloud.ctx.order.entity.OrderUser;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController{
	
	@RequestMapping(value = "/addOrder",method = RequestMethod.POST)
	OrderContext addOrder(@RequestBody OrderDto orderDto) {
		return new OrderContext(orderDto.getOrderUser(), orderDto.getOrderPayPip());
	}
}

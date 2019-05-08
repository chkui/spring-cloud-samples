package chkui.spring.cloud.ctx.order.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import chkui.spring.cloud.ctx.order.context.OrderContext;
import chkui.spring.cloud.ctx.order.dto.OrderDto;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController{
	Map<String, OrderContext> contextCache;
	
	@PostConstruct
	private void init() {
		contextCache = new HashMap<>();
	}

	@RequestMapping(value = "/add",method = RequestMethod.POST)
	String addOrder(@RequestBody OrderDto orderDto) {
		String key = UUID.randomUUID().toString().replaceAll("-", "");
		//1. 创建一个订单，写入高速缓存
		OrderContext ctx = new OrderContext();
		ctx.setOrderId(key);
		ctx.setOrderState(OrderContext.OrderState.Regist);
		//ctx.setOrderUser(orderDto.getOrderUser());
		//ctx.setOrderPayPip(orderDto.getOrderPayPip());
		
		contextCache.put(key, ctx);
		
		//2. 执行一个异步过程，将缓存写入物理数据库 //TODO
		return key;
	}

	@RequestMapping(value = "/set/{key}/{state}",method = RequestMethod.POST)
	OrderContext setOrderState(@PathVariable("key")String key, @PathVariable("state") OrderContext.OrderState state) {
		OrderContext ctx = contextCache.get(key);
		ctx.setOrderState(state);
		return ctx;
	}

	@RequestMapping(value = "/get/{key}",method = RequestMethod.GET)
	OrderContext getOrder(@PathVariable("key") String key) {
		return contextCache.get(key);
	}
}

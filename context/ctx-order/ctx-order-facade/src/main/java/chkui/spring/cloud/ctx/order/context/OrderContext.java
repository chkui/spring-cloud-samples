package chkui.spring.cloud.ctx.order.context;

import chkui.spring.cloud.ctx.order.entity.OrderPayPip;
import chkui.spring.cloud.ctx.order.entity.OrderUser;

public class OrderContext {
	private OrderUser orderUser;
	
	private OrderPayPip orderPayPip;

	public OrderContext() {}
	
	public OrderContext(OrderUser orderUser, OrderPayPip orderPayPip) {
		super();
		this.orderUser = orderUser;
		this.orderPayPip = orderPayPip;
	}

	public OrderUser getOrderUser() {
		return orderUser;
	}

	public void setOrderUser(OrderUser orderUser) {
		this.orderUser = orderUser;
	}

	public OrderPayPip getOrderPayPip() {
		return orderPayPip;
	}

	public void setOrderPayPip(OrderPayPip orderPayPip) {
		this.orderPayPip = orderPayPip;
	}
}

package chkui.spring.cloud.ctx.order.dto;

import chkui.spring.cloud.ctx.order.entity.OrderAccount;
import chkui.spring.cloud.ctx.order.entity.OrderDivided;
import chkui.spring.cloud.ctx.order.entity.OrderPayPip;
import chkui.spring.cloud.ctx.order.entity.OrderTerminal;
import chkui.spring.cloud.ctx.order.entity.OrderUser;

public class OrderDto {
	private OrderUser orderUser;
	private OrderDivided orderDivided;
	private OrderAccount orderAccount;
	private OrderPayPip orderPayPip;
	private OrderTerminal orderTerminal;

	public OrderUser getOrderUser() {
		return orderUser;
	}

	public void setOrderUser(OrderUser orderUser) {
		this.orderUser = orderUser;
	}

	public OrderDivided getOrderDivided() {
		return orderDivided;
	}

	public void setOrderDivided(OrderDivided orderDivided) {
		this.orderDivided = orderDivided;
	}

	public OrderAccount getOrderAccount() {
		return orderAccount;
	}

	public void setOrderAccount(OrderAccount orderAccount) {
		this.orderAccount = orderAccount;
	}

	public OrderPayPip getOrderPayPip() {
		return orderPayPip;
	}

	public void setOrderPayPip(OrderPayPip orderPayPip) {
		this.orderPayPip = orderPayPip;
	}

	public OrderTerminal getOrderTerminal() {
		return orderTerminal;
	}

	public void setOrderTerminal(OrderTerminal orderTerminal) {
		this.orderTerminal = orderTerminal;
	}
}

package chkui.spring.cloud.ctx.order.context;

public class OrderContext {
	
	public static enum OrderState {
		Regist,PrePay,CompletePay,Release
	}
	
	private String orderId;
	
	private OrderState orderState;

	public OrderContext() {}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public OrderState getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}
}

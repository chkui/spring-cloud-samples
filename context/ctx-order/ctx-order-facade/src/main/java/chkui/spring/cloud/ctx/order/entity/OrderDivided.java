package chkui.spring.cloud.ctx.order.entity;

import java.io.Serializable;

/**
 * 订单分润模型
 * @author chenkui
 *
 */
public class OrderDivided implements Serializable {
	private static final long serialVersionUID = 9087283708921959103L;

	public static enum DividedType {
		percent,
		regular
	}
	
	private long relateUser;
	
	private DividedType type;
	
	private float param;
	
	public OrderDivided() {}

	public OrderDivided(long relateUser, DividedType type, float param) {
		super();
		this.relateUser = relateUser;
		this.type = type;
		this.param = param;
	}

	public long getRelateUser() {
		return relateUser;
	}

	public void setRelateUser(long relateUser) {
		this.relateUser = relateUser;
	}

	public DividedType getType() {
		return type;
	}

	public void setType(DividedType type) {
		this.type = type;
	}

	public float getParam() {
		return param;
	}

	public void setParam(float param) {
		this.param = param;
	}
}

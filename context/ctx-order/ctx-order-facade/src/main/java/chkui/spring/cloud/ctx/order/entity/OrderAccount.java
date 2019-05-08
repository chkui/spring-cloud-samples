package chkui.spring.cloud.ctx.order.entity;

import java.io.Serializable;

/**
 * 账户信息
 * @author chenkui
 *
 */
public class OrderAccount implements Serializable {
	private static final long serialVersionUID = 694353184913943714L;

	private String code;
	
	private String des;

	public OrderAccount() {}
	
	public OrderAccount(String code, String des) {
		super();
		this.code = code;
		this.des = des;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
}

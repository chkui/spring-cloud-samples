package chkui.spring.cloud.ctx.user.entity;

import java.io.Serializable;

/**
 * 支付渠道
 * @author chenkui
 *
 */
public class PayPip implements Serializable{
	private static final long serialVersionUID = -2005678442016800236L;

	/**
	 * 渠道类型
	 */
	private PipType pipType;
	
	/**
	 * 渠道对应的编码
	 */
	private String pipCode;
	
	public static enum PipType {
		creditCard,
		debitCard,
		online,
		uepay
	}

	public PayPip(PipType pipType, String pipCode) {
		super();
		this.pipType = pipType;
		this.pipCode = pipCode;
	}

	public PipType getPipType() {
		return pipType;
	}

	public void setPipType(PipType pipType) {
		this.pipType = pipType;
	}

	public String getPipCode() {
		return pipCode;
	}

	public void setPipCode(String pipCode) {
		this.pipCode = pipCode;
	}
}

package chkui.spring.cloud.ctx.user.entity;

import java.io.Serializable;

public class Terminal implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7189423573806400206L;

	private String snCode;
	
	private String address;
	
	private long proxyUserId;
	
	public Terminal() {}

	public Terminal(String snCode, String address, long proxyUserId) {
		super();
		this.snCode = snCode;
		this.address = address;
		this.proxyUserId = proxyUserId;
	}

	public String getSnCode() {
		return snCode;
	}

	public void setSnCode(String snCode) {
		this.snCode = snCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getProxyUserId() {
		return proxyUserId;
	}

	public void setProxyUserId(long proxyUserId) {
		this.proxyUserId = proxyUserId;
	}
}

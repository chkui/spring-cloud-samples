package chkui.spring.cloud.evolution.entity;

/**
 * 订单信息，用于存放订单的相关数据
 * @author 陈葵
 *
 */
public class OrderBase {
	/**
	 * 订单ID
	 */
	private long id;
	
	/**
	 * 订单信息
	 */
	private String msg;
	
	/**
	 * 服务端口
	 */
	private int port;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}

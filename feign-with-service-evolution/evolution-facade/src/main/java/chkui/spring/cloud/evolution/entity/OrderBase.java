package chkui.spring.cloud.evolution.entity;

/**
 * 订单的基础结构类
 * @author chkui
 *
 */
public class OrderBase {
	/**
	 * 订单ID
	 */
	private long id;
	
	/**
	 * 订单消息
	 */
	private String msg;
	
	/**
	 * 服务端口
	 */
	private int port;
	
	private String extMsg;

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

	public String getExtMsg() {
		return extMsg;
	}

	public void setExtMsg(String extMsg) {
		this.extMsg = extMsg;
	}
}

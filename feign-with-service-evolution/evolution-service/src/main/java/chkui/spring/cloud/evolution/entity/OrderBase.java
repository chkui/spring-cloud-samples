package chkui.spring.cloud.evolution.entity;

/**
 * ������Ϣ�����ڴ�Ŷ������������
 * @author �¿�
 *
 */
public class OrderBase {
	/**
	 * ����ID
	 */
	private long id;
	
	/**
	 * ������Ϣ
	 */
	private String msg;
	
	/**
	 * ����˿�
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
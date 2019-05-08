package chkui.spring.cloud.ctx.order.entity;

import java.io.Serializable;

/**
 * 用户结构信息
 * @author chkui
 */
public class OrderUser implements Serializable  {
	private static final long serialVersionUID = 2926306517621980744L;

	public static enum UserType {
		client, merchant, proxy, 
	}
	
	private long id;
	
	private UserType type;
	
	private String name;
	
	public OrderUser() {}
	
	public OrderUser(long id, UserType type, String name) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
}

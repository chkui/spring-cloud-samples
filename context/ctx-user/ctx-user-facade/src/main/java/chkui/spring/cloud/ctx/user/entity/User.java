package chkui.spring.cloud.ctx.user.entity;

import java.io.Serializable;
import java.util.Set;

/**
 * 用户结构信息
 * @author chkui
 *
 */
public class User implements Serializable  {
	private static final long serialVersionUID = 2926306517621980744L;

	public static enum UserType {
		client, merchant, proxy, 
	}
	
	private long id;
	
	private UserType type;
	
	private String name;
	
	private String des;
	
	public User() {}
	
	public User(long id, UserType type, String name, String des) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.des = des;
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
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
}

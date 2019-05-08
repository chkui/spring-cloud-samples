package chkui.spring.cloud.ctx.user.context;

import chkui.spring.cloud.ctx.user.entity.PayPip;
import chkui.spring.cloud.ctx.user.entity.User;

/**
 * 用户信息上下文
 * 上下文是由“时态”的
 * @author 陈葵
 *
 */
public class UserContext{
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public User getUser() {return null;}
	public PayPip getPayPip(){return null;}
}

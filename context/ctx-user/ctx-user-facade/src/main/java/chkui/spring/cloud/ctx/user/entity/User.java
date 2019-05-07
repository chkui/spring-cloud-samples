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
	
	private Set<Account> accounts;
	
	private Set<Terminal> terminal;
	
	private Set<PayPip> payPip;
	
	private Set<OrderDivided> divided;
	
	public User(long id, UserType type, String name, String des, Set<Account> accounts, Set<Terminal> terminal,
			Set<PayPip> payPip, Set<OrderDivided> divided) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.des = des;
		this.accounts = accounts;
		this.terminal = terminal;
		this.payPip = payPip;
		this.divided = divided;
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
	public Set<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
	public Set<Terminal> getTerminal() {
		return terminal;
	}
	public void setTerminal(Set<Terminal> terminal) {
		this.terminal = terminal;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	public Set<PayPip> getPayPip() {
		return payPip;
	}
	public void setPayPip(Set<PayPip> payPip) {
		this.payPip = payPip;
	}
	public Set<OrderDivided> getDivided() {
		return divided;
	}
	public void setDivided(Set<OrderDivided> divided) {
		this.divided = divided;
	}
}

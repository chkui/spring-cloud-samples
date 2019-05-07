package chkui.spring.cloud.ctx.user.context;

import java.io.Serializable;
import java.util.Set;

import chkui.spring.cloud.ctx.user.entity.Account;
import chkui.spring.cloud.ctx.user.entity.OrderDivided;
import chkui.spring.cloud.ctx.user.entity.PayPip;
import chkui.spring.cloud.ctx.user.entity.Terminal;
import chkui.spring.cloud.ctx.user.entity.User;

/**
 * 用户信息上下文
 * 上下文是由“时态”的
 * @author 陈葵
 *
 */
public class UserContext implements Serializable {
	private static final long serialVersionUID = 4679384283247777360L;
	
	private User user;

	private Set<Account> accounts;

	private Set<Terminal> terminal;
	
	private Set<PayPip> payPip;
	
	private Set<OrderDivided> divided;

	public UserContext() {}
	
	public UserContext(User user,  Set<Account> accounts, Set<Terminal> terminal, Set<PayPip> payPip,
			Set<OrderDivided> divided) {
		this.accounts = accounts;
		this.user = user;
		this.terminal = terminal;
		this.payPip = payPip;
		this.divided = divided;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Terminal> getTerminal() {
		return terminal;
	}

	public void setTerminal(Set<Terminal> terminal) {
		this.terminal = terminal;
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

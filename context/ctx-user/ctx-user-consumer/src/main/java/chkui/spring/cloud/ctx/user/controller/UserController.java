package chkui.spring.cloud.ctx.user.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import chkui.spring.cloud.ctx.user.entity.Account;
import chkui.spring.cloud.ctx.user.entity.OrderDivided;
import chkui.spring.cloud.ctx.user.entity.OrderDivided.DividedType;
import chkui.spring.cloud.ctx.user.entity.PayPip;
import chkui.spring.cloud.ctx.user.entity.Terminal;
import chkui.spring.cloud.ctx.user.entity.User;
import chkui.spring.cloud.ctx.user.entity.User.UserType;
import chkui.spring.cloud.ctx.user.interfaces.UserInterface;

@RestController
public class UserController implements UserInterface{

	@Autowired
	UserCache userCache;

	@Override
	public User getUserBySnCode(String snCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUserId(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}

@Service
class TerminalCache {
	public User getUserBySnCode(String snCode) {
		
	}
	
	private long loadTerminalMapperUser (String snCode) {
		
	}

	private long localMemCache (String snCode) {
		
	}
	
	private long remoteCache (String snCode) {
		
	}
	
	private long buildFromDb(String snCode) {
		
	}
}

@Service
class UserCache {
	
	public User getUserById(long id) {
		return this.localMemCache(id);
	}
	
	/**
	 * 从本地缓存获取
	 * @param id
	 * @return
	 */
	private User localMemCache(long id){
		//查询缓存
		User user = remoteCache(id);
		//写入缓存
		return user;
	}
	
	private User remoteCache(long id) {
		//查询缓存
		User user = buildFromDb(id);
		//写入缓存
		return user; //TODO
	}
	
	/**
	 * 从数据库组装数据
	 * @param id
	 * @return
	 */
	private User buildFromDb(long id) {
		Set<Account> accounts = new HashSet<Account>();
		accounts.add(new Account("0123456789", "账户1"));
		accounts.add(new Account("9876543210", "账户2"));
		Set<PayPip> payPips = new HashSet<PayPip>();
		payPips.add(new PayPip(PayPip.PipType.creditCard, "12345678"));
		Set<Terminal> terminal = new HashSet<Terminal>();
		terminal.add(new Terminal("SN12345", "广州", 123456L));
		Set<OrderDivided> divided = new HashSet<OrderDivided>();
		divided.add(new OrderDivided(123, DividedType.percent, 0.08f));
		
		
		User user = new User(id, UserType.merchant, "Alice", "Alice Uepay Des", accounts, terminal, payPips,  divided);
		return user;
	}
}

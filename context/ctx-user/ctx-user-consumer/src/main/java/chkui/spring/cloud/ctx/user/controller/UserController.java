package chkui.spring.cloud.ctx.user.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import chkui.spring.cloud.ctx.user.context.UserContext;
import chkui.spring.cloud.ctx.user.entity.Account;
import chkui.spring.cloud.ctx.user.entity.Divided;
import chkui.spring.cloud.ctx.user.entity.PayPip;
import chkui.spring.cloud.ctx.user.entity.Terminal;
import chkui.spring.cloud.ctx.user.entity.User;
import chkui.spring.cloud.ctx.user.entity.User.UserType;

@RestController
@RequestMapping(value = "/api/user")
public class UserController{

	@Autowired
	private UserContextCache userCache;
	
	@Autowired
	private TerminalCache terminalCache;

	@RequestMapping(value = "/sncode/{snCode}",method = RequestMethod.GET)
	public UserContext getUserBySnCode(String snCode) {
		return terminalCache.getUserBySnCode(snCode);
	}

	@RequestMapping(value = "/userid/{id}",method = RequestMethod.GET)
	public UserContext getUserByUserId(Long userId) {
		return userCache.getUserById(userId);
	}
}

/**
 * 透明多级缓存案例（TMC），也称为链路缓存(cache chain)
 * @author 陈葵
 *
 */
@Service
class TerminalCache {
	
	@Autowired
	private UserContextCache userCache;
	
	public UserContext getUserBySnCode(String snCode) {
		long userId = loadTerminalMapperUser(snCode);
		return userCache.getUserById(userId);
	}
	
	private long loadTerminalMapperUser (String snCode) {
		return localMemCache(snCode);
	}

	private long localMemCache (String snCode) {
		//1. 预读缓存
		//2. 从下游缓存获取数据
		long userId = remoteCache(snCode);
		//3. 写本级缓存
		return userId;
	}
	
	private long remoteCache (String snCode) {
		//1. 预读缓存
		//2. 从下游缓存获取数据
		long userId = buildFromDb(snCode);
		//3. 写本级缓存
		return userId;
	}
	
	//如果前序缓存都没有数据，只能从物理数据库组织数据（一次sql）
	private long buildFromDb(String snCode) {
		return 1234567L; 
	}
}

@Service
class UserContextCache {
	
	public UserContext getUserById(long id) {
		return this.localMemCache(id);
	}
	
	/**
	 * 从本地缓存获取
	 * @param id
	 * @return
	 */
	private UserContext localMemCache(long id){
		//查询缓存
		UserContext user = remoteCache(id);
		//写入缓存
		return user;
	}
	
	private UserContext remoteCache(long id) {
		//查询缓存
		UserContext user = buildFromDb(id);
		//写入缓存
		return user; //TODO
	}
	
	/**
	 * 从数据库组装数据
	 * @param id
	 * @return
	 */
	private UserContext buildFromDb(long id) {
		Set<Account> accounts = new HashSet<Account>();
		accounts.add(new Account("0123456789", "账户1"));
		accounts.add(new Account("9876543210", "账户2"));
		Set<PayPip> payPip = new HashSet<PayPip>();
		payPip.add(new PayPip(PayPip.PipType.creditCard, "12345678"));
		Set<Terminal> terminal = new HashSet<Terminal>();
		terminal.add(new Terminal("SN12345", "广州", 123456L));
		Set<Divided> divided = new HashSet<Divided>();
		divided.add(new Divided(123, Divided.DividedType.percent, 0.08f));
		User user = new User(id, UserType.merchant, "Alice", "Alice Uepay Des");
		
		UserContext ctx = new UserContext(user, accounts, terminal, payPip, divided);
		
		return ctx;
	}
}

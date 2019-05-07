package chkui.spring.cloud.ctx.user.interfaces;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import chkui.spring.cloud.ctx.user.entity.User;

/**
 * 订单先关的服务
 * @author chkui
 * 
 */
@FeignClient("user-consumer")

@RequestMapping(value = "/api/user")
public interface UserInterface {
	/**
	 * 通过Sn编号获取用户信息
	 * @param id 要获取的订单对应的id
	 * @return
	 */
	@RequestMapping(value = "/getUser/{snCode}",method = RequestMethod.GET)
	User getUserBySnCode(@PathVariable("snCode")String snCode);

	/**
	 * 通过用户id获取用户信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getUser/{id}",method = RequestMethod.GET)
	User getUserByUserId(@PathVariable("id")Long id);
}

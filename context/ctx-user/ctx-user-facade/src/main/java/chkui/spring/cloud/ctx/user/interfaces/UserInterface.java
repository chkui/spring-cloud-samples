package chkui.spring.cloud.ctx.user.interfaces;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import chkui.spring.cloud.ctx.user.context.UserContext;

/**
 * 
 * @author chkui
 * 
 */
@FeignClient("user-consumer")
@RequestMapping(value = "/api/user")
public interface UserInterface {

	@RequestMapping(value = "/test/{snCode}",method = RequestMethod.GET)
	String test(@PathVariable("snCode")String snCode);
	/**
	 * 
	 * @param id 
	 * @return
	 */
	@RequestMapping(value = "/sncode/{snCode}",method = RequestMethod.GET)
	UserContext getUserBySnCode(@PathVariable("snCode")String snCode);

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/userid/{id}",method = RequestMethod.GET)
	UserContext getUserByUserId(@PathVariable("id")Long id);
}

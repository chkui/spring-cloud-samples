package chkui.spring.cloud.ctx.user.interfaces;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import chkui.spring.cloud.ctx.user.context.UserContext;
import chkui.spring.cloud.ctx.user.entity.User;

/**
 * 璁㈠崟鍏堝叧鐨勬湇鍔�
 * @author chkui
 * 
 */
@FeignClient("user-consumer")
public interface UserInterface {
	/**
	 * 閫氳繃Sn缂栧彿鑾峰彇鐢ㄦ埛淇℃伅
	 * @param id 瑕佽幏鍙栫殑璁㈠崟瀵瑰簲鐨刬d
	 * @return
	 */
	@RequestMapping(value = "/api/user/getUserbySnCode/{snCode}",method = RequestMethod.GET)
	UserContext getUserBySnCode(@PathVariable("snCode")String snCode);

	/**
	 * 閫氳繃鐢ㄦ埛id鑾峰彇鐢ㄦ埛淇℃伅
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/api/user/getUserByUserId/{id}",method = RequestMethod.GET)
	UserContext getUserByUserId(@PathVariable("id")Long id);
}

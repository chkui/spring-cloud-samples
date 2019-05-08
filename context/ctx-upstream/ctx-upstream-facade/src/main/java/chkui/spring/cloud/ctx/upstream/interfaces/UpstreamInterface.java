package chkui.spring.cloud.ctx.upstream.interfaces;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author chkui
 * 
 */
@FeignClient("upstream-consumer")
@RequestMapping(value = "/api/upstream")
public interface UpstreamInterface {

	@RequestMapping(value = "/dual/{orderKey}",method = RequestMethod.GET)
    public int upstream(@PathVariable("orderKey")String orderKey);
}

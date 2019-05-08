package chkui.spring.cloud.ctx.transaction.interfaces;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author chkui
 * 
 */
@FeignClient("transaction-consumer")
@RequestMapping(value = "/api/transaction")
public interface TransactionInterface {

	@RequestMapping(value = "/dual/{orderKey}",method = RequestMethod.GET)
    public int transaction(@PathVariable("orderKey")String orderKey);
}

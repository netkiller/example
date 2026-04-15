package cn.netkiller.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "spring-cloud-consul-producer", fallback = FeignFallback.class)
public interface TestFeign {
	@RequestMapping(value = "/hi", method = RequestMethod.GET)
	String hi(@RequestParam(value = "name") String name);
}

package cn.netkiller.openfeign.feign;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("microservice-restful")
@RibbonClient(name = "eureka-server")
public interface TestOpenfeign {
	@GetMapping("/config")
	String config();
	
	@GetMapping("/add")
	public Integer add(@RequestParam Integer a, @RequestParam Integer b);
}

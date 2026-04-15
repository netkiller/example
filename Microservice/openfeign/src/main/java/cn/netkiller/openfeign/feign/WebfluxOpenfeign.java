package cn.netkiller.openfeign.feign;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import reactor.core.publisher.Mono;

@FeignClient("webflux")
@RibbonClient(name = "eureka-server")
public interface WebfluxOpenfeign {
	
	@GetMapping("/name")
	public Mono<String> name();
	
}

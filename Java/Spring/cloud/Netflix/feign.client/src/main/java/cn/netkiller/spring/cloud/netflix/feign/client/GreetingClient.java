package cn.netkiller.spring.cloud.netflix.feign.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("test-service")
public interface GreetingClient {
	@RequestMapping("/greeting")
	String greeting();
}
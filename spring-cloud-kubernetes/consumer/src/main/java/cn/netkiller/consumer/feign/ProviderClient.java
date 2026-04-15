package cn.netkiller.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "provider", fallback = ProviderClientFallback.class)
public interface ProviderClient {
	@GetMapping("/ping")
	String ping();
}

@Component
class ProviderClientFallback implements ProviderClient {

	@Override
	public String ping() {
		return "Error";
	}
}

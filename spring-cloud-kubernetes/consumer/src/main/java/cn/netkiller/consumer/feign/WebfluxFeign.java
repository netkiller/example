package cn.netkiller.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@FeignClient(name = "webflux", fallback = WebfluxFallback.class)
public interface WebfluxFeign {

	@GetMapping("/services")
	public Flux<String> services();

	@GetMapping("/ping")
	public Mono<String> ping();
}

@Component
class WebfluxFallback implements WebfluxFeign {

	@Override
	public Flux<String> services() {
		return Flux.just("Error");
	}

	@Override
	public Mono<String> ping() {
		return Mono.just("Error");
	}
}
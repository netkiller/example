package cn.netkiller.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.consumer.feign.WebfluxFeign;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
//@Slf4j
@RequestMapping("/webflux")
public class WebfluxController {

	@Autowired
	private WebfluxFeign webfluxFeign;

	@GetMapping("/")
	public Mono<String> index() {
		return Mono.just("Hello World");
	}

	@GetMapping("/ping")
	public Mono<String> ping() {
		return webfluxFeign.ping();
	}

	@GetMapping("/service")
	public Flux<String> sayHelloWorld() {
		return webfluxFeign.services();
	}
}

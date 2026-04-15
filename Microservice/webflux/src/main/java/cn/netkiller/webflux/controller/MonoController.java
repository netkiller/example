package cn.netkiller.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/mono")
public class MonoController {

	public MonoController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/just")
	public Mono<String> sayHelloWorld() {
		return Mono.just("Hello World");
	}
}

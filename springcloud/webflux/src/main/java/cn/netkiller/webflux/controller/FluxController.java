package cn.netkiller.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/flux")
public class FluxController {

	public FluxController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/just")
	public Flux<String> just() {

		return Flux.just("Hello", "World");
	}
}

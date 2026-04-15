package cn.netkiller.webflux.controller;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

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

	@GetMapping("/randomNumbers")
	public Flux<ServerSentEvent<Integer>> randomNumbers() {
		return Flux.interval(Duration.ofSeconds(1)).map(new Function<Long, Object>() {
			public Object apply(Long seq) {
				return Tuples.of(seq, ThreadLocalRandom.current().nextInt());
			}
		}).map(new Function<Object, ServerSentEvent<Integer>>() {
			public ServerSentEvent<Integer> apply(Object data) {
				return ServerSentEvent.<Integer>builder().event("random")
						.id(Long.toString(((Tuple2<Long, Integer>) data).getT1()))
						.data(((Tuple2<Long, Integer>) data).getT2()).build();
			}
		});
	}
}

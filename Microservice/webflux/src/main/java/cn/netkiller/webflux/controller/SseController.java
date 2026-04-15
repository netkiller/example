package cn.netkiller.webflux.controller;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

@RestController
@RequestMapping("/sse")
public class SseController {
	private int count_down = 10;

	public SseController() {

	}

	@GetMapping(value = "/launch", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ServerSentEvent<Object>> countDown() {

		return Flux.interval(Duration.ofSeconds(1)).map(seq -> Tuples.of(seq, getCountDownSec())).map(data -> ServerSentEvent.<Object>builder().event("launch").id(Long.toString(data.getT1())).data(data.getT2().toString()).build());
	}

	private String getCountDownSec() {
		if (count_down > 0) {
			count_down--;
			return "倒计时：" + count_down;
		}
		return "发射";
	}

	@GetMapping("/random")
	public Flux<ServerSentEvent<Integer>> randomNumbers() {
		return Flux.interval(Duration.ofSeconds(1)).map(seq -> Tuples.of(seq, ThreadLocalRandom.current().nextInt())).map(data -> ServerSentEvent.<Integer>builder().event("random").id(Long.toString(data.getT1())).data(data.getT2()).build());
	}

	@GetMapping("/range")
	public Flux<Object> range() {
		return Flux.range(10, 1).map(seq -> Tuples.of(seq, getCountDownSec())).map(data -> ServerSentEvent.<Object>builder().event("launch").id(Long.toString(data.getT1())).data(data.getT2().toString()).build());
	}

}

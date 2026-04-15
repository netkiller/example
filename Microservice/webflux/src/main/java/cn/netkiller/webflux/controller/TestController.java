package cn.netkiller.webflux.controller;

import java.util.List;
import java.util.Random;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class TestController {

	@Autowired
	DiscoveryClient client;

	@Value("${name:Neo default}")
	private String name;

//	@Value("${nickname}")
//	private String nickname;

	public TestController() {

	}

	@GetMapping("/")
	@ResponseBody
	public Publisher<String> index() {
		return Mono.just("Hello world!");
	}

	@GetMapping("/name")
	public Mono<String> name() {
		return Mono.just(this.name);
	}

//	@GetMapping("/nickname")
//	public Mono<String> nickname() {
//		return Mono.just(this.nickname);
//	}

	@GetMapping("/info")
	public Mono<String> hello() {
		List<ServiceInstance> instances = client.getInstances("webflux");
		ServiceInstance selectedInstance = instances.get(new Random().nextInt(instances.size()));
		return Mono.just("Hello World: " + selectedInstance.getServiceId() + ":" + selectedInstance.getHost() + ":"
				+ selectedInstance.getPort());
	}

}

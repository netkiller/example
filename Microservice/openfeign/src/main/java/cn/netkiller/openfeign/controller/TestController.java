package cn.netkiller.openfeign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.openfeign.feign.TestOpenfeign;
import cn.netkiller.openfeign.feign.WebfluxOpenfeign;
import reactor.core.publisher.Mono;

@RestController
public class TestController {
	@Autowired
	private TestOpenfeign testOpenfeign;

	@GetMapping("/")
	public String index() {
		return "Hello world!!!";
	}

	@GetMapping("/config")
	public String config() {
		return this.testOpenfeign.config();
	}

	@GetMapping("/add")
	public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
		return testOpenfeign.add(a, b);
	}

	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@GetMapping("/lb")
	public String LoadBalancer() {
		ServiceInstance serviceInstance = this.loadBalancerClient.choose("web");
		System.out.println("Server: " + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":" + serviceInstance.getPort());

		return serviceInstance.toString();
	}

	@Autowired
	private WebfluxOpenfeign webfluxOpenfeign;

	@GetMapping("/webflux/name")
	public Mono<String> name() {
		return this.webfluxOpenfeign.name();
	}

}

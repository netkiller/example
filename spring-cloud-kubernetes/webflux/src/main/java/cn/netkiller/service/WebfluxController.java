package cn.netkiller.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
//@Slf4j
public class WebfluxController {

//	private static final Log log = LogFactory.getLog(HelloController.class);

	@Autowired
	private DiscoveryClient discoveryClient;

	@Value("${homepage}")
	private String homepage;

	@GetMapping("/")
	public Mono<String> index() {
		return Mono.just("Webflux OK!");
	}

	@GetMapping("/ping")
	public Mono<String> ping() {
		try {
			return Mono.just(InetAddress.getLocalHost().getHostName() + "\r\n");
		} catch (UnknownHostException e) {
			return Mono.just("Pong\r\n");
		}
	}

	@GetMapping("/services")
	public Flux<String> services() {
		return Flux.fromStream(this.discoveryClient.getServices().stream());
	}

	@GetMapping("/info")
	public Mono<String> hello() {
		List<ServiceInstance> instances = discoveryClient.getInstances("webflux");
		ServiceInstance selectedInstance = instances.get(new Random().nextInt(instances.size()));
		return Mono.just(
				selectedInstance.getServiceId() + ":" + selectedInstance.getHost() + ":" + selectedInstance.getPort());
	}

	@GetMapping("/config")
	public Mono<String> config() {
		return Mono.just(this.homepage + "\r\n");
	}
}

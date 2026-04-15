package cn.netkiller.consumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.consumer.feign.ProviderClient;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ConsumerController {
	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private ProviderClient providerClient;

	@GetMapping("/")
	public String index() {
		return "Consumer OK\r\n";
	}

	@GetMapping("/service")
	public Object getClient() {
		return discoveryClient.getServices();
	}

	@GetMapping("/instance")
	public List<ServiceInstance> getInstance(@RequestParam("instanceId") String instanceId) {
		return discoveryClient.getInstances(instanceId);
	}

	@GetMapping("/ping")
	public String ping() {
		return OperationResponse.builder().status(true).data(providerClient.ping()).build();
	}

}

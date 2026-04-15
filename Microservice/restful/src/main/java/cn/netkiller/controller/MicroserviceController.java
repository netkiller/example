package cn.netkiller.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MicroserviceController implements HealthIndicator {

	private static Logger logger = LoggerFactory.getLogger(MicroserviceController.class);

	@Autowired
	private DiscoveryClient discoveryClient;

	@GetMapping("/ping")
	public String ping() {
		logger.info("PONE");
		return "pong";
	}

	@GetMapping("/services")
	public List<String> services() {
		return this.discoveryClient.getServices();
	}

	@Override
	public Health health() {
		return Health.up().withDetail("hello", "world").build();
	}
}

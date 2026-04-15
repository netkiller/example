package cn.netkiller.welcome.component;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class RestfulApiCounter {
	private final Counter counter;

	public RestfulApiCounter(MeterRegistry registry) {
		this.counter = registry.counter("restful_api_requests_total");
	}

	public void increment() {
		this.counter.increment();
	}
}

package cn.netkiller.welcome;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.MeterRegistry;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class Application {

	@GetMapping("/")
	@ResponseBody
	public Publisher<String> index() {
		return Mono.just("Hello world! \r\n");
	}

	@GetMapping("/address")
	@ResponseBody
	public Publisher<String> address() throws UnknownHostException {
		InetAddress addr = InetAddress.getLocalHost();
		return Mono.just(String.format("Address %s, Hostname %s \r\n", addr.getHostAddress(), addr.getHostName()));
	}

	@Bean
	MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName) {
		return (registry) -> registry.config().commonTags("application", applicationName);
	}

	public static void main(String[] args) {
		System.out.println("Welcome!");
		SpringApplication.run(Application.class, args);
	}
}

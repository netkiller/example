package cn.netkiller.spring.cloud.netflix.feign.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RestController
public class Application {
	@Autowired
	private GreetingClient greetingClient;

	@RequestMapping("/get-greeting")
	public String greeting() {
		return greetingClient.greeting();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("Hello World!");
	}
}
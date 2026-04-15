package feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("common.feign")
public class Application {

	public static void main(String[] args) {
		System.out.println("Feign Starting...");
		SpringApplication.run(Application.class, args);
	}
}

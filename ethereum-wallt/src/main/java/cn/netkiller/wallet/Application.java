package cn.netkiller.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
// @EnableAsync
// @EnableEurekaClient
// @EnableFeignClients("common.feign")
@ComponentScan()
// @EntityScan("common.domain")
public class Application {

	public Application() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		System.out.println("Wallet Starting...");
		SpringApplication.run(Application.class, args);
	}
}

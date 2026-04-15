package cn.netkiller.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// @EnableScheduling
// @EnableEurekaClient
// @EnableFeignClients("common.feign")
// @ComponentScan({ "web", "common.domain", "common.service" })
// @EntityScan("common.domain")
// @EnableJpaRepositories(basePackages = { "common.repository" })
public class Application {
	public static void main(String[] args) {
		System.out.println("Web Starting...");
		SpringApplication.run(Application.class, args);
	}
}

package cn.netkiller.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableEurekaClient
@ComponentScan({ "api", "common.domain", "common.service" })
@EntityScan("common.domain")
@EnableJpaRepositories(basePackages = { "common.repository" })
public class Application {

	public static void main(String[] args) {
		System.out.println("Service Api Starting...");
		SpringApplication.run(Application.class, args);
	}
}

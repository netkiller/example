package cn.netkiller.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableEurekaClient
@EntityScan("common.domain")
public class Application {

	public static void main(String[] args) {
		System.out.println("Service Schedule Starting...");
		SpringApplication.run(Application.class, args);
	}
}
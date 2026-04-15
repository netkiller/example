package cn.netkiller.openfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients

public class Application {
	public static void main(String[] args) {
		System.out.println("openfeign!");
		SpringApplication.run(Application.class, args);
	}
}

package cn.netkiller.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WebfluxApplication {
	public static void main(String[] args) {
		System.out.println("Webflux!");
		SpringApplication.run(WebfluxApplication.class, args);
	}
}

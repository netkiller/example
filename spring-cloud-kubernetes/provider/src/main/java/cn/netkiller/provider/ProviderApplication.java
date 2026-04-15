package cn.netkiller.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProviderApplication {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		SpringApplication.run(ProviderApplication.class, args);
	}
}

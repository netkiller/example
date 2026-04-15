package cn.netkiller.openfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class OpenfeignApplication {
	public static void main(String[] args) {
		System.out.println("Openfeign!");
		SpringApplication.run(OpenfeignApplication.class, args);
	}
}

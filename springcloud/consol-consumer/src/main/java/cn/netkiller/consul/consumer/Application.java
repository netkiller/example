package cn.netkiller.consul.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		System.out.println("Consol Consumer!");
		SpringApplication.run(Application.class, args);
	}
}

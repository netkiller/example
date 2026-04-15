package cn.netkiller.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		SpringApplication.run(Application.class, args);
	}
}

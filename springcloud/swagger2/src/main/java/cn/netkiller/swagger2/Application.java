package cn.netkiller.swagger2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		System.out.println("Swagger2!");
		SpringApplication.run(Application.class, args);
	}
}

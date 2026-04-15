package cn.netkiller.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class Application {
	public static void main(String[] args) {
		System.out.println("Zuul!");
		SpringApplication.run(Application.class, args);
	}
}

package cn.netkiller.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		System.out.println("Starting GraphQL !");
		new SpringApplication(Application.class).run(args);
	}
}

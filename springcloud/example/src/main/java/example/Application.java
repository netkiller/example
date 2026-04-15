package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		System.out.println("Spring boot with Oauth2RestTemplate!");
		SpringApplication.run(Application.class, args);
	}
}
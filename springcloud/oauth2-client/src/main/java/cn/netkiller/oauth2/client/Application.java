package cn.netkiller.oauth2.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableAutoConfiguration
// @EnableRedisHttpSession
@EnableOAuth2Client
public class Application {
	public static void main(String[] args) {
		System.out.println("Oauth2 Client!");
		SpringApplication.run(Application.class, args);
	}
}

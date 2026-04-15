package cn.netkiller.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableAutoConfiguration
//@EnableRedisHttpSession 
public class Application {
	public static void main(String[] args) {
		System.out.println("Oauth2 !");
		SpringApplication.run(Application.class, args);
	}
}

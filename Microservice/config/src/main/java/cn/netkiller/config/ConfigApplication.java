package cn.netkiller.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigApplication {
	public static void main(String[] args) {
		System.out.println("Config Server Starting...");
		SpringApplication.run(ConfigApplication.class, args);
	}
}

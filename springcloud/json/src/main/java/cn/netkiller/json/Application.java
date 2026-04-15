package cn.netkiller.json;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("cn.netkiller.json.model")
@SpringBootApplication(scanBasePackages = { "cn.netkiller.json.controller" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
}

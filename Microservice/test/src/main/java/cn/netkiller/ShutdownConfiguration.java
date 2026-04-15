package cn.netkiller;

import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ShutdownConfiguration {

	public ShutdownConfiguration() {
		// TODO Auto-generated constructor stub
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("==============================");
		System.out.println("Destroying Spring");
		System.out.println("==============================");
	}

}

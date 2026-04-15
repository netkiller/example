package cn.netkiller.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		TestService bean = context.getBean(TestService.class);
		bean.test1();
		bean.test2("xsx");
		bean.test3("xsx", 1);
		bean.test4("xsx2", 1, 2, 3, 4);
	}
}
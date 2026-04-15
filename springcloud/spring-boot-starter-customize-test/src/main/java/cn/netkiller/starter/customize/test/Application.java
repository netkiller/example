package cn.netkiller.starter.customize.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import cn.netkiller.autoconfigure.EnableSms;
import cn.netkiller.sms.SmsSender;

@SpringBootApplication
@EnableSms
public class Application {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);

		SmsSender smsSender = applicationContext.getBean(SmsSender.class);
		smsSender.send("验证码发送成功！");
	}
}

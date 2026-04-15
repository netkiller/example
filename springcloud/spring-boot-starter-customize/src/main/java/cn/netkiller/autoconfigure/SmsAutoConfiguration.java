package cn.netkiller.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.netkiller.sms.SmsSender;

@EnableConfigurationProperties(value = SmsProperties.class)
@Configuration
public class SmsAutoConfiguration {

	@Autowired
	private SmsProperties smsProperties;

	@Bean
	public SmsSender send() {
		return new SmsSender(this.smsProperties);
	}
}

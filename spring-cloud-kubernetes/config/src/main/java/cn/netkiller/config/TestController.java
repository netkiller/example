package cn.netkiller.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties(KeyValueConfig.class)
//@RefreshScope
public class TestController {
	@Autowired
	private KeyValueConfig keyValueConfig;

	@GetMapping("/")
	public String index() {
		return "Hello world\r\n";
	}

	@GetMapping("/hello")
	public String hello() {
		return keyValueConfig.getMessage() + " [" + new SimpleDateFormat().format(new Date()) + "]";
	}
}

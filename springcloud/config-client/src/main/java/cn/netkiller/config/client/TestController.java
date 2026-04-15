package cn.netkiller.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Value("${foo}")
	String foo;

	@RequestMapping("/foo")
	public String hi() {
		return foo;
	}
}
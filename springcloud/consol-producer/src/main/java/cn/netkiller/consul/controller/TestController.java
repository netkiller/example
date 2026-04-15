package cn.netkiller.consul.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	public TestController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/hello")
	public String provider() {
		return "Helloworld!!!";
	}

	@RequestMapping("/hi")
	public String hi(@RequestParam(name = "name") String name) {
		return "hi " + name + "!";
	}

}

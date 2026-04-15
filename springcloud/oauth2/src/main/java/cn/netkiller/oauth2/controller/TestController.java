package cn.netkiller.oauth2.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	public TestController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping("/message")
	public Map<String, Object> dashboard() {
		return Collections.<String, Object>singletonMap("message", "Yay!");
	}

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

	@RequestMapping("/dashboard/login")
	public String dashboard1() {
		return "redirect:/#/";
	}
}

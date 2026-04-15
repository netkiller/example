package cn.netkiller.oauth2.controller;

import org.springframework.boot.actuate.trace.http.HttpTrace.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@GetMapping("/")
	public Principal index(Principal principal) {
		return principal;
	}

	@GetMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}
}
package example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestfulController {
	@Autowired
	private OAuth2RestOperations oAuth2RestTemplate;

	@GetMapping("/test/oauth2")
	public String index() {
		return oAuth2RestTemplate.getForObject("http://localhost:8080/user", String.class);
	}
}

package cn.netkiller.oauth2.client;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;

	@RequestMapping("/")
	public Principal home(Principal principal) {
		System.out.println("Hello " + principal.getName());

		return principal;
	}

	@RequestMapping("/index")
	public ClientRegistration index() {
		ClientRegistration clientRegistration = this.clientRegistrationRepository.findByRegistrationId("netkiller");

		return clientRegistration;
	}
}

package cn.netkiller.oauth2.client;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = { "APP-CLIENT-ID=my-client-id", "APP-CLIENT-SECRET=my-client-secret", "YAHOO-CLIENT-ID=my-yahoo-client-id", "YAHOO-CLIENT-SECRET=my-yahoo-client-secret" })
public class ApplicationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void everythingShouldRedirectToLogin() {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
		assertThat(entity.getHeaders().getLocation()).isEqualTo(URI.create("http://localhost:" + this.port + "/login"));
	}

	@Test
	public void loginShouldHaveAllOAuth2ClientsToChooseFrom() {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/login", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).contains("/oauth2/authorization/yahoo");
		assertThat(entity.getBody()).contains("/oauth2/authorization/github-client-1");
		assertThat(entity.getBody()).contains("/oauth2/authorization/github-client-2");
		assertThat(entity.getBody()).contains("/oauth2/authorization/github-repos");
	}

}
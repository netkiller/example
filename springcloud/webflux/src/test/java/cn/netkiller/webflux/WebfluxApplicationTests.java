package cn.netkiller.webflux;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebfluxApplicationTests {

	@Test
	public void contextLoads() {
	}

	private WebTestClient webTestClient;

	@Before
	public void setUp() {
		this.webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
	}

	@Test
	public void sample() throws Exception {
		this.webTestClient.get().uri("/").exchange().expectStatus().isOk().expectBody(String.class).isEqualTo("Hello world!");
	}

	@Test
	public void client() {

	}
}

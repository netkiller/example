//package cn.netkiller.oauth2.simple;
//
//import org.junit.runner.RunWith;
//import org.springframework.util.LinkedMultiValueMap;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class AppTest extends TestCase {
//
//	public AppTest(String testName) {
//		super(testName);
//	}
//
//	public static Test suite() {
//		return new TestSuite(AppTest.class);
//	}
//
//	public void testApp() {
//		assertTrue(true);
//	}
//
//	@Autowired
//	private TestRestTemplate restTemplate;
//
//	@Test
//	public void token_password() {
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("grant_type", "password");
//		params.add("username", "admin");
//		params.add("password", "admin");
//		params.add("scope", "scope1 scope2");
//		String response = restTemplate.withBasicAuth("clientId", "clientSecret").postForObject("/oauth/token", params, String.class);
//		System.out.println(response);
//	}
//
//	@Test
//	public void token_client() {
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("grant_type", "client_credentials");
//		String response = restTemplate.withBasicAuth("clientId", "clientSecret").postForObject("/oauth/token", params, String.class);
//		System.out.println(response);
//	}
//
//}

package example.config;

import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableOAuth2Client
@Configuration
public class OAuth2Config {

	@Value("${oauth.resource:http://localhost:8082}")
	private String baseUrl;
	@Value("${oauth.authorize:http://localhost:8082/oauth/authorize}")
	private String authorizeUrl;
	// @Value("${oauth.token:http://localhost:8082/oauth/token}")
	private String tokenUrl = "http://api.alpha.5kwords.com/oauth/token";

	@Bean
	protected OAuth2ProtectedResourceDetails resource() {

		ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();

		resource.setAccessTokenUri(tokenUrl);
		resource.setClientId("api");
		resource.setClientSecret("secret");
		resource.setGrantType("password");
		resource.setScope(asList("read", "write"));

		resource.setUsername("netkiller@msn.com");
		resource.setPassword("123456");

		return resource;
	}

	@Bean
	public OAuth2RestOperations restTemplate() {
		AccessTokenRequest accessTokenRequest = new DefaultAccessTokenRequest();
		return new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(accessTokenRequest));
	}

}

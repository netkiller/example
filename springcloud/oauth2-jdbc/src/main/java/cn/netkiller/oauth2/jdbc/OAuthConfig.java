package cn.netkiller.oauth2.jdbc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableOAuth2Client
public class OAuthConfig {

	@Value("${OAuth2ClientId}")
	private String oAuth2ClientId;

	@Value("${OAuth2ClientSecret}")
	private String oAuth2ClientSecret;

	@Value("${Oauth2AccesTokenUri}")
	private String accessTokenUri;

	@Bean
	public RestTemplate oAuthRestTemplate() {
		ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
		resourceDetails.setId("1");
		resourceDetails.setClientId(oAuth2ClientId);
		resourceDetails.setClientSecret(oAuth2ClientSecret);
		resourceDetails.setAccessTokenUri(accessTokenUri);

		/*
		 * 
		 * When using @EnableOAuth2Client spring creates a OAuth2ClientContext for us:
		 * 
		 * "The OAuth2ClientContext is placed (for you) in session scope to keep the state for different users separate. Without that you would have to manage the equivalent data structure yourself on the server, mapping incoming requests to users, and associating each user with a separate instance of the OAuth2ClientContext." (http://projects.spring.io/spring-security-oauth/docs/oauth2.html#client-configuration)
		 * 
		 * Internally the SessionScope works with a threadlocal to store variables, hence a new thread cannot access those. Therefore we can not use @Async
		 * 
		 * Solution: create a new OAuth2ClientContext that has no scope. Note: this is only safe when using client_credentials as OAuth grant type!
		 * 
		 */

		// OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, oauth2ClientContext);
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, new DefaultOAuth2ClientContext());

		return restTemplate;
	}
}
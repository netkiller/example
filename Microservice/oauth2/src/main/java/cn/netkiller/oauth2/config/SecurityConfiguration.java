package cn.netkiller.oauth2.config;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.keys.KeyManager;
import org.springframework.security.crypto.keys.StaticKeyGeneratingKeyManager;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Order(100)
@EnableWebSecurity
@Configuration
@Import(OAuth2AuthorizationServerConfiguration.class)
public class SecurityConfiguration {
	@Bean
	public RegisteredClientRepository registeredClientRepository() {
		RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
				//
				.clientId("client").clientSecret("secret")
				//
				.clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
				// Authorization Code Grant Type
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				// Client Credentials Grant Type
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS).redirectUri("http://example.com")
				.scope("message.read").scope("message.write").build();
		return new InMemoryRegisteredClientRepository(registeredClient);
	}

	@Bean
	public KeyManager keyManager() {
		return new StaticKeyGeneratingKeyManager();
	}

	@Bean
	public UserDetailsService users() {
//		User.withUserDetails(userDetails)
		UserDetails user = User.withDefaultPasswordEncoder()
				// Username
				.username("user")
				// Password
				.password("password")
				// roles
				.roles("USER").build();
		return new InMemoryUserDetailsManager(user);
	}
}
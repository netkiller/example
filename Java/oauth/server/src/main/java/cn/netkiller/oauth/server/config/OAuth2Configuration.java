package cn.netkiller.oauth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Value("${oauth.tokenTimeout:3600}")
	private int expiration;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {
		configurer.authenticationManager(authenticationManager);
		configurer.userDetailsService(userDetailsService);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// clients.inMemory().withClient("api").secret("secret").accessTokenValiditySeconds(expiration).scopes("read",
		// "write").authorizedGrantTypes("password",
		// "refresh_token").resourceIds("resource");
		// Public client where client secret is vulnerable (e.g. mobile apps, browsers)
		clients.inMemory().withClient("api").secret("secret").accessTokenValiditySeconds(expiration).scopes("read", "write").authorizedGrantTypes("implicit", "refresh_token", "password", "authorization_code", "client_credentials").resourceIds("resource");
	}

	// curl -X POST --user 'public:secret' -d 'grant_type=client_credentials&username=netkiller@msn.com&password=123456' http://localhost:8000/api/oauth/token
}

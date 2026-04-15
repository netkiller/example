package cn.netkiller.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import cn.netkiller.oauth2.service.OauthUserDetailsService;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private OauthUserDetailsService userDetailsService;

//	@Autowired
//	private RedisConnectionFactory connectionFactory;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		// String secret = passwordEncoder().encode("secret");
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String secret = passwordEncoder.encode("secret");

		clients.inMemory()
				// 配置验证码模式
				.withClient("sso").secret(secret) // 认证
				.authorizedGrantTypes("authorization_code") // 允许验证码授权类型
				.scopes("read") // 允许的授权范围
				.autoApprove(true) // 自动接受
				.redirectUris("http://www.netkiller.cn", "http://www.example.com", "http://localhost:8080/login/oauth2/code/netkiller");
	}

	@Override
	public void configure(final AuthorizationServerSecurityConfigurer security) throws Exception {
		// super.configure(security);
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
	}

	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailsService).allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
//		endpoints.tokenStore(tokenStore());
	}

	// @Override
	// public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	// endpoints.tokenStore(tokenStore());
	// }

//	@Bean
//	public TokenStore tokenStore() {
//		return new RedisTokenStore(connectionFactory);
//	}

}

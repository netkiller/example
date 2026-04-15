package cn.netkiller.oauth2.jwt.authorization;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {
	// @Autowired
	// DataSource dataSource;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// clients.jdbc(dataSource);

		String secret = passwordEncoder().encode("secret");
		clients.inMemory()
				// 配置验证码模式
				.withClient("sso").secret(secret) // 认证
				.authorizedGrantTypes("authorization_code", "refresh_token") // 允许验证码授权类型
				.scopes("read") // 允许的授权范围
				.autoApprove(true) // 自动接受
				.redirectUris("http://localhost:8080/login/oauth2/code/netkiller");
		// 配置密码验证模式
		// .and().withClient("api").secret(secret).authorizedGrantTypes("password", "refresh_token").scopes("read", "write").resourceIds("resource").accessTokenValiditySeconds(3600);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore()).accessTokenConverter(jwtAccessTokenConverter()).userDetailsService(userDetailsService);
	}

	@Bean
	public TokenStore tokenStore() {
		return new RedisTokenStore(redisConnectionFactory);
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey("123456");
		return jwtAccessTokenConverter;
	}

	// @Bean
	// public JwtAccessTokenConverter jwtAccessTokenConverter() {
	// JwtAccessTokenConverter converter = new CustomTokenEnhancer();
	// converter.setKeyPair(new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "password".toCharArray()).getKeyPair("jwt"));
	// return converter;
	// }

	// @Bean
	// public TokenStore tokenStore() {
	// return new JwtTokenStore(jwtTokenEnhancer());
	// }
	//
	// @Bean
	// protected JwtAccessTokenConverter jwtTokenEnhancer() {
	// //注意此处需要相应的jks文件
	// KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("fzp-jwt.jks"), "fzp123".toCharArray());
	// JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	// converter.setKeyPair(keyStoreKeyFactory.getKeyPair("fzp-jwt"));
	// return converter;
	// }

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.allowFormAuthenticationForClients();// 允许客户表单认证
		security.passwordEncoder(passwordEncoder());// 设置oauth_client_details中的密码编码器
//		security.checkTokenAccess("permitAll()").checkTokenAccess("isAuthenticated()");// 对于CheckEndpoint控制器[框架自带的校验]的/oauth/check端点允许所有客户端发送器请求而不会被Spring-security拦截
		// security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
	}
}

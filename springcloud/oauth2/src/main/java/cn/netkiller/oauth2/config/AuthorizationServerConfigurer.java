package cn.netkiller.oauth2.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 认证服务器
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

	public AuthorizationServerConfigurer() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private AuthenticationManager authenticationManager;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// @Autowired
	// private UserDetailsService userDetailsService;

	// @Autowired
	// ClientDetailsService clientDetailsService;
	//
	// @Autowired
	// private RedisConnectionFactory connectionFactory;
	//
	// @Bean
	// public TokenStore tokenStore() {
	// RedisTokenStore redis = new RedisTokenStore(connectionFactory);
	// return redis;
	// }

	// @Primary
	// @Bean
	// DefaultTokenServices tokenServices() {
	// DefaultTokenServices d = new DefaultTokenServices();
	// d.setClientDetailsService(clientDetailsService);// 如果没有设置此项，则client设置的token有效期无效
	// d.setTokenStore(tokenStore());
	// d.setReuseRefreshToken(true);// 是否重复使用refresh_token
	// d.setSupportRefreshToken(true);// 是否支持refresh_token,为false则refresh_token无法使用
	// return d;
	// }
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients().passwordEncoder(passwordEncoder());
	}

	// @Override
	// public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	// security.tokenKeyAccess("permitAll()")// 对于CheckEndpoint控制器[框架自带的校验]的/oauth/token端点允许所有客户端发送器请求而不会被Spring-security拦截
	// .checkTokenAccess("isAuthenticated()")// 要访问/oauth/check_token必须设置为permitAll(),此接口一般不对外公布，是springoauth2内部使用，因此这里要设为isAuthenticated()
	// .allowFormAuthenticationForClients()// 允许客户表单认证,不加的话/oauth/token无法访问
	// .passwordEncoder(passwordEncoder);// 设置oauth_client_details中的密码编码器
	//
	// }

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// clients.withClientDetails(clientDetailsService);
		String secret = passwordEncoder().encode("secret");
		clients.inMemory().withClient("api").secret(secret).accessTokenValiditySeconds(3600).scopes("read", "write").authorizedGrantTypes("password", "refresh_token").resourceIds("resource");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {
		configurer.authenticationManager(authenticationManager);
		// configurer.userDetailsService(userDetailsService);
		configurer.userDetailsService(userDetailsService());
		// configurer.pathMapping("/oauth/token","/oauth/token3"); //可以修改默认/oauth/token路径为 /oauth/token3
	}

	@Bean
	public UserDetailsService userDetailsService() {
		Map<String, String[]> users = new HashMap<String, String[]>() {
			{
				put("user", new String[] { "ROLE_USER" });
				put("admin", new String[] { "ROLE_USER", "ROLE_ADMIN" });
				put("client", new String[] { "ROLE_CLIENT" });
				put("trust", new String[] { "ROLE_TRUSTED_CLIENT" });
			}
		};
		String password = passwordEncoder().encode("123456");

		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
				// TODO 这里的UserDetails需要自行进行封装
				String[] authList = users.containsKey(name) ? users.get(name) : new String[] { "ROLE_USER" };
				User user = new User(name, password, AuthorityUtils.createAuthorityList(authList));

				// TODO 这里需要自行定义访问数据库的扩展
				// 通过用户名获取用户信息
				// Member member = memberRepository.findByName(name);
				// if (member != null) {
				// User user = new User(member.getName(), member.getPassword(),AuthorityUtils.createAuthorityList(member.getRoles()));
				// return user;
				// } else {
				// throw new UsernameNotFoundException("用户[" + name + "]不存在");
				// }
				return user;
			}
		};

	}

	// @Override
	// public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	// // endpoints.tokenServices(tokenServices())
	// endpoints.authenticationManager(authenticationManager);
	// // // endpoints.pathMapping("/oauth/token","/oauth/token3");//可以修改默认的endpoint路径
	// //
	// }

}

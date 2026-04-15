//package cn.netkiller.oauth2.client;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
//
//
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {
//
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().anyRequest().authenticated().and().requestMatchers().antMatchers("/api/**","/admin");
//	}
//
//	@Primary
//	@Bean
//	public RemoteTokenServices remoteTokenServices() {
//		RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//		remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
//		remoteTokenServices.setClientId("sso");
//		remoteTokenServices.setClientSecret("secret");
//		return remoteTokenServices;
//	}
//	//
//	// @Override
//	// public void configure(HttpSecurity http) throws Exception {
//	// //
//	// // // http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and().authorizeRequests().anyRequest().authenticated().and().requestMatchers().antMatchers("/callback", "/api/**");
//	// //
//	// http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and().authorizeRequests().anyRequest().permitAll();
//	// // // http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and().requestMatchers().antMatchers("/**").and().authorizeRequests().filterSecurityInterceptorOncePerRequest(true).antMatchers("/api/**").permitAll().and().headers().frameOptions().disable();
//	// // http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and().requestMatchers().antMatchers("/callback", "/res/**").and().authorizeRequests().antMatchers("/callback", "/res/**").authenticated();
//	//
//	// // http.csrf().disable()
//	// // // 验证所有请求
//	// // .authorizeRequests().anyRequest().authenticated()
//	// // // 允许访问首页
//	// // .antMatchers("/", "/login").permitAll().and()
//	// // // 设置登出URL为 /logout
//	// // .logout().logoutUrl("/logout").permitAll().logoutSuccessUrl("/").and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//	//
//	// }
//	//
//}

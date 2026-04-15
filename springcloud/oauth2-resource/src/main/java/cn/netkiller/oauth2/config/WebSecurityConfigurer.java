//package cn.netkiller.oauth2.config;
//
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//@Configuration
//@EnableOAuth2Sso
//public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
//
//	@Bean
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
//
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		// http.authorizeRequests().antMatchers("/", "/login**", "/test").permitAll().anyRequest().authenticated();
//
//		// http.authorizeRequests().anyRequest().authenticated().and().oauth2Login().redirectionEndpoint().baseUri("/custom-callback");
//
//		http.
//		// 禁用 CSRF 跨站伪造请求，便于测试
//				csrf().disable()
//				// 验证所有请求
//				.authorizeRequests().anyRequest().authenticated()
//				// 允许访问首页
//				.antMatchers("/", "/login", "/callback").permitAll().and()
//				// 设置登出URL为 /logout
//				.logout().logoutUrl("/logout").permitAll().logoutSuccessUrl("/").and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//	}
//
//}

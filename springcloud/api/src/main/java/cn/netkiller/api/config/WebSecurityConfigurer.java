package cn.netkiller.api.config;
//package api.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		// web.ignoring().antMatchers("/static/**", "/**/*.jsp");
//	}
//
//	protected void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//		// auth.inMemoryAuthentication().withUser("user1").password("secret1").roles("USER").and().withUser("user2").password("secret2").roles("USER").and().withUser("admin").password("secret").roles("ADMIN");
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/ping", "/v1/*/ping", "/public/**").permitAll().anyRequest().authenticated().and().rememberMe().and().httpBasic().and().csrf().disable();
//	}
//
//}

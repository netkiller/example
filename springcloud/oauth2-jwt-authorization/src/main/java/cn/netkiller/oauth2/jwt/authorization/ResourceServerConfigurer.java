//package cn.netkiller.oauth2.jwt.authorization;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.requestMatchers().antMatchers("/user").and().authorizeRequests().anyRequest().access("#oauth2.hasScope('read')").and().anonymous().disable();
//	}
//
//}

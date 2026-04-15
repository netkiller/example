package cn.netkiller.oauth2.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTrace.Principal;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAuthorizationServer
public class Application {

	public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		@Override
		public void configure(HttpSecurity httpSecurity) throws Exception {
			httpSecurity.formLogin().and().csrf().disable();
			// httpSecurity.csrf().disable().authorizeRequests().antMatchers("/", "/oauth/**", "/login", "/user", "/health").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll();
		}

	}

	@RestController
	public class TestController {
		@GetMapping("/")
		public Principal index(Principal principal) {
			return principal;
		}

		@GetMapping("/user")
		public Principal user(Principal principal) {
			return principal;
		}
	}

	public static void main(String[] args) {
		System.out.println("Hello World!");
		SpringApplication.run(Application.class, args);
	}
}

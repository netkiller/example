//package cn.netkiller.oauth2.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.context.SecurityContextPersistenceFilter;
//import org.springframework.security.web.csrf.CsrfFilter;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//import javax.annotation.Resource;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.web.csrf.CsrfToken;
//import org.springframework.security.web.csrf.CsrfTokenRepository;
//import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableAutoConfiguration(exclude = { org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class })
//@Component
//@EnableOAuth2Sso
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//	public WebSecurityConfiguration() {
//		// TODO Auto-generated constructor stub
//	}
//
//	// public void configure(HttpSecurity http) throws Exception {
//	// http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**").permitAll().anyRequest().authenticated();
//	// }
//
//	@Autowired
//	private SimpleCORSFilter simpleCORSFilter;
//
//	@Resource(name = "userService")
//	private UserDetailsService userDetailsService;
//
//	@Override
//	@Bean
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
//
//	@Autowired
//	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
//		auth.parentAuthenticationManager(authenticationManagerBean());
//	}
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		// 解决静态资源被拦截的问题
//		web.ignoring().antMatchers("/assets/**");
//		web.ignoring().antMatchers("/favicon.ico");
//
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().requestMatchers() // requestMatchers 配置 数组
//				.antMatchers("/oauth/**", "/login", "/home").and().authorizeRequests() // authorizeRequests 配置权限 顺序为先配置需要放行的url 在配置需要权限的url，最后再配置.anyRequest().authenticated()
//				.antMatchers("/oauth/**").authenticated().and().formLogin().loginPage("/login").permitAll();
//		http.addFilterBefore(simpleCORSFilter, SecurityContextPersistenceFilter.class);
//	}
//
//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Component
//	public class SimpleCORSFilter extends OncePerRequestFilter {
//
//		@Override
//		protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//			httpServletRequest.setCharacterEncoding("utf-8");
//			httpServletResponse.setCharacterEncoding("utf-8");
//			httpServletResponse.setHeader("Content-Type", "application/json");
//			httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");// 允许所以域名访问，
//			httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");// 允许的访问方式
//			httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type,Authorization");
//			httpServletResponse.setHeader("Access-Control-Request-Headers", "x-requested-with,content-type,Accept,Authorization");
//			httpServletResponse.setHeader("Access-Control-Request-Method", "GET,POST,PUT,DELETE,OPTIONS");
//			filterChain.doFilter(httpServletRequest, httpServletResponse);
//		}
//	}
//
//	// @Override
//	// public void configure(HttpSecurity http) throws Exception {
//	// http.antMatcher("/dashboard/**").authorizeRequests().anyRequest().authenticated().and().csrf().csrfTokenRepository(csrfTokenRepository()).and().addFilterAfter(csrfHeaderFilter(), CsrfFilter.class).logout().logoutUrl("/dashboard/logout").permitAll().logoutSuccessUrl("/");
//	// }
//
//	// private Filter csrfHeaderFilter() {
//	// return new OncePerRequestFilter() {
//	// @Override
//	// protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//	// CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
//	// if (csrf != null) {
//	// Cookie cookie = new Cookie("XSRF-TOKEN", csrf.getToken());
//	// cookie.setPath("/");
//	// response.addCookie(cookie);
//	// }
//	// filterChain.doFilter(request, response);
//	// }
//	// };
//	// }
//	//
//	// private CsrfTokenRepository csrfTokenRepository() {
//	// HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
//	// repository.setHeaderName("X-XSRF-TOKEN");
//	// return repository;
//	// }
//}

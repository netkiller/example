//package cn.netkiller.oauth2.config;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletRequest;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
//import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
//import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
//import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//import org.springframework.stereotype.Component;
//
//@Configuration
//@EnableResourceServer // 注解来开启资源服务器
//public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//
//	public ResourceServerConfiguration() {
//		// TODO Auto-generated constructor stub
//	}
//
//	private static final String RESOURCE_ID = "resource_id";
//	@Autowired
//	private DefaultTokenServices tokenServices;
//	@Autowired
//	private TokenStore tokenStore;
//	@Autowired
//	private PermitAuthenticationFilter permitAuthenticationFilter;
//
//	@Override
//	public void configure(ResourceServerSecurityConfigurer resources) {
//		resources.resourceId(RESOURCE_ID).stateless(true).tokenServices(tokenServices);
//	}
//
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//
//		// 配置那些资源需要保护的
//		http.requestMatchers().antMatchers("/api/**").and().authorizeRequests().antMatchers("/api/**").authenticated().and().exceptionHandling().accessDeniedHandler(customAccessDeniedHandler()) // 权限认证失败业务处理
//				.authenticationEntryPoint(customAuthenticationEntryPoint()); // 认证失败的业务处理
//		http.addFilterBefore(permitAuthenticationFilter, AbstractPreAuthenticatedProcessingFilter.class); // 自定义token过滤 token校验失败后自定义返回数据格式
//
//	}
//
//	@Bean
//	public LogoutSuccessHandler customLogoutSuccessHandler() {
//		return new CustomLogoutSuccessHandler();
//	}
//
//	@Bean
//	public AuthenticationFailureHandler customLoginFailHandler() {
//		return new CustomLoginFailHandler();
//	}
//
//	@Bean
//	public OAuth2AuthenticationEntryPoint customAuthenticationEntryPoint() {
//		return new CustomAuthenticationEntryPoint();
//	}
//
//	@Bean
//	public OAuth2AccessDeniedHandler customAccessDeniedHandler() {
//		return new CustomAccessDenieHandler();
//	}
//
//	/**
//	 * 重写 token 验证失败后自定义返回数据格式
//	 * 
//	 * @return
//	 */
//	@Bean
//	public WebResponseExceptionTranslator webResponseExceptionTranslator() {
//		return new DefaultWebResponseExceptionTranslator() {
//			@Override
//			public ResponseEntity translate(Exception e) throws Exception {
//				ResponseEntity responseEntity = super.translate(e);
//				OAuth2Exception body = (OAuth2Exception) responseEntity.getBody();
//				HttpHeaders headers = new HttpHeaders();
//				headers.setAll(responseEntity.getHeaders().toSingleValueMap());
//				// do something with header or response
//				if (401 == responseEntity.getStatusCode().value()) {
//					// 自定义返回数据格式
//					Map<String, String> map = new HashMap<>();
//					map.put("status", "401");
//					map.put("message", e.getMessage());
//					map.put("timestamp", String.valueOf(LocalDateTime.now()));
//					return new ResponseEntity(JSON.toJSONString(map), headers, responseEntity.getStatusCode());
//				} else {
//					return new ResponseEntity(body, headers, responseEntity.getStatusCode());
//				}
//			}
//		};
//	}
//
//	@Component
//	public class CustomAuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {
//
//		@Override
//		public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//			// log.info(" ====================================================== ");
//			// log.info("请求url：" + httpServletRequest.getRequestURI());
//			// log.info(" ============ 身份认证失败..................... ");
//			// log.info(e.getMessage());
//			// log.info(e.getLocalizedMessage());
//			e.printStackTrace();
//			Map<String, String> map = new HashMap<>();
//			map.put("status", "401");
//			map.put("message", e.getMessage());
//			map.put("path", httpServletRequest.getServletPath());
//			map.put("timestamp", String.valueOf(LocalDateTime.now()));
//			httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//			ResultUtil.writeJavaScript(httpServletResponse, map);
//		}
//
//	}
//
//	@Component
//	public class PermitAuthenticationFilter extends OAuth2AuthenticationProcessingFilter {
//
//		private static final String BEARER_AUTHENTICATION = "Bearer ";
//		private static final String HEADER_AUTHORIZATION = "authorization";
//		private TokenExtractor tokenExtractor = new BearerTokenExtractor();
//		private boolean stateless = true;
//		OAuth2AuthenticationManager oAuth2AuthenticationManager = new OAuth2AuthenticationManager();
//		@Autowired
//		private TokenStore tokenStore;
//
//		public PermitAuthenticationFilter() {
//			DefaultTokenServices dt = new DefaultTokenServices();
//			dt.setTokenStore(tokenStore);
//			oAuth2AuthenticationManager.setTokenServices(dt);
//			this.setAuthenticationManager(oAuth2AuthenticationManager);
//		}
//
//		@Override
//		public void init(FilterConfig filterConfig) throws ServletException {
//
//		}
//
//		@Override
//		public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//			HttpServletResponse response = (HttpServletResponse) servletResponse;
//			HttpServletRequest request = (HttpServletRequest) servletRequest;
//			log.info(" ================== =========================== ===================");
//			log.info("当前访问的URL地址：" + request.getRequestURI());
//			Authentication authentication = this.tokenExtractor.extract(request);
//			if (authentication == null) {
//				if (this.stateless && this.isAuthenticated()) {
//					// SecurityContextHolder.clearContext();
//				}
//				log.info("当前访问的URL地址：" + request.getRequestURI() + "不进行拦截...");
//				filterChain.doFilter(request, response);
//			} else {
//				log.info("************　开始验证token　..........................   ");
//				String accessToken = request.getParameter("access_token");
//				String headerToken = request.getHeader(HEADER_AUTHORIZATION);
//				Map<String, String> map = new HashMap<>();
//				map.put("status", "403");
//				AtomicBoolean error = new AtomicBoolean(false);
//				if (StringUtils.isNotBlank(accessToken)) {
//					try {
//						OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
//						log.info("token =" + oAuth2AccessToken.getValue());
//					} catch (InvalidTokenException e) {
//						error.set(true);
//						map.put("message", e.getMessage());
//						log.info("** 无校的token信息.　** ");
//						// throw new AccessDeniedException("无校的token信息.");
//					}
//
//				} else if (StringUtils.isNotBlank(headerToken) && headerToken.startsWith(BEARER_AUTHENTICATION)) {
//					try {
//						OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(headerToken.split(" ")[0]);
//						log.info("token =" + oAuth2AccessToken.getValue());
//					} catch (InvalidTokenException e) {
//						error.set(true);
//						map.put("message", e.getMessage());
//						log.info("** 无校的token信息.　** ");
//						// throw new AccessDeniedException("无校的token信息.");
//					}
//
//				} else {
//					error.set(true);
//					map.put("message", "参数无token.");
//					log.info("** 参数无token.　** ");
//					// throw new AccessDeniedException("参数无token.");
//				}
//				if (!error.get()) {
//					filterChain.doFilter(request, response);
//				} else {
//					map.put("path", request.getServletPath());
//					map.put("timestamp", String.valueOf(LocalDateTime.now()));
//					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//					ResultUtil.writeJavaScript(response, map);
//				}
//			}
//		}
//
//		@Override
//		public void destroy() {
//
//		}
//
//		private boolean isAuthenticated() {
//			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//			return authentication != null && !(authentication instanceof AnonymousAuthenticationToken);
//		}
//	}
//
//	public class ResultUtil {
//
//		/**
//		 * 将json输出到前端(参数非json格式)
//		 * 
//		 * @param response
//		 * @param obj
//		 *            任意类型
//		 */
//		public static void writeJavaScript(HttpServletResponse response, Object obj) {
//			response.setContentType("application/json;charset=UTF-8");
//			response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
//			response.addHeader("Cache-Control", "post-check=0, pre-check=0");
//			response.setHeader("Pragma", "no-cache");
//			/* 设置浏览器跨域访问 */
//			response.setHeader("Access-Control-Allow-Origin", "*");
//			response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT");
//			response.setHeader("Access-Control-Max-Age", "3600");
//			response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
//			response.setHeader("Access-Control-Allow-Credentials", "true");
//			try {
//				PrintWriter out = response.getWriter();
//				out.write(JSON.toJSONString(obj));
//				out.flush();
//				out.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//}

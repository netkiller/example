package cn.netkiller.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {

	@RequestMapping("/hystrixfallback")
	public String hystrixfallback() {
		return "This is a fallback";
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes().route(r -> r.path("/spring").uri("http://www.netkiller/spring/")).build();
	}

	// @Bean
	// public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
	// //@formatter:off
	// return builder.routes()
	// .route("path_route", r -> r.path("/get")
	// .uri("http://httpbin.org"))
	// .route("host_route", r -> r.host("*.myhost.org")
	// .uri("http://httpbin.org"))
	// .route("rewrite_route", r -> r.host("*.rewrite.org")
	// .filters(f -> f.rewritePath("/foo/(?<segment>.*)",
	// "/${segment}"))
	// .uri("http://httpbin.org"))
	// .route("hystrix_route", r -> r.host("*.hystrix.org")
	// .filters(f -> f.hystrix(c -> c.setName("slowcmd")))
	// .uri("http://httpbin.org"))
	// .route("hystrix_fallback_route", r -> r.host("*.hystrixfallback.org")
	// .filters(f -> f.hystrix(c -> c.setName("slowcmd").setFallbackUri("forward:/hystrixfallback")))
	// .uri("http://httpbin.org"))
	//// .route("limit_route", r -> r
	//// .host("*.limited.org").and().path("/anything/**")
	//// .filters(f -> f.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter())))
	//// .uri("http://httpbin.org"))
	// .route("websocket_route", r -> r.path("/echo")
	// .uri("ws://localhost:9000"))
	// .build();
	// //@formatter:on
	// }

	// @Bean
	// RedisRateLimiter redisRateLimiter() {
	// return new RedisRateLimiter(1, 2);
	// }

	// @Bean
	// SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
	// return http.httpBasic().and()
	// .csrf().disable()
	// .authorizeExchange()
	// .pathMatchers("/anything/**").authenticated()
	// .anyExchange().permitAll()
	// .and()
	// .build();
	// }
	//
	// @Bean
	// public MapReactiveUserDetailsService reactiveUserDetailsService() {
	// UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build();
	// return new MapReactiveUserDetailsService(user);
	// }

	public static void main(String[] args) {
		System.out.println("Spring Cloud Gateway!");
		SpringApplication.run(Application.class, args);
	}
}
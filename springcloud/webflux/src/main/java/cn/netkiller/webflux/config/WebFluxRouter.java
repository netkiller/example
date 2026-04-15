package cn.netkiller.webflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import cn.netkiller.webflux.component.HelloWorldHandler;

@Configuration
public class WebFluxRouter {

	public WebFluxRouter() {
	}

	@Bean
	public RouterFunction<ServerResponse> routeHelloWorld(HelloWorldHandler helloWorldHandler) {
//		return RouterFunctions.route(RequestPredicates.GET("/"), (request) -> ServerResponse.status(HttpStatus.OK).syncBody("Hello, World"));
		return RouterFunctions.route(RequestPredicates.GET("/hello").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), helloWorldHandler::helloWorld);
	}
}

package cn.netkiller.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class Swagger2Configuration {
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public").apiInfo(apiInfo()).select().paths(postPaths()).build();
	}

	private Predicate<String> postPaths() {
		return or(regex("/api/.*"), regex("/public/api/.*"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Open API").description("Open API reference for developers").termsOfServiceUrl("http://api.netkiller.cn").contact(new Contact("Neo Chen", "http://www.netkiller.cn", "netkiller@msn.com")).license("Mit License").licenseUrl("").version("1.0").build();
	}

}

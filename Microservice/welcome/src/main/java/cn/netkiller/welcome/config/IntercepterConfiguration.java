package cn.netkiller.welcome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IntercepterConfiguration implements WebMvcConfigurer {

	@Bean
	public PrometheusInterceptor prometheusInterceptor() {

		return new PrometheusInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(prometheusInterceptor()).addPathPatterns("/**");

	}
}

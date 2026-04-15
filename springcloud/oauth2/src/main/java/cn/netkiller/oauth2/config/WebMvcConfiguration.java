//package cn.netkiller.oauth2.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebMvcConfiguration implements WebMvcConfigurer {
//
//	public WebMvcConfiguration() {
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/home").setViewName("home");
//		registry.addViewController("/login").setViewName("login"); // 自定义的登陆页面
//		registry.addViewController("/oauth/confirm_access").setViewName("oauth_approval"); // 自定义的授权页面
//		registry.addViewController("/oauth_error").setViewName("oauth_error");
//	}
//
//}

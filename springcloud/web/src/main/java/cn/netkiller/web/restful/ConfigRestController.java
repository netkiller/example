package cn.netkiller.web.restful;
//package web.restful;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RefreshScope
//@RestController
//@RequestMapping("/config")
//public class ConfigRestController {
//	
//	@Value("${cms.api.url:Hello default}")
//	private String message;
//	
//	@Value("${cms.site_id:site_id}")
//	private String siteId;
//	
//
//	@RequestMapping("/test")
//	public String ping() {
//		System.out.println(this.message);
//		return (this.message);
//	}
//	
//	@RequestMapping("/test1")
//	public String test1() {
//		System.out.println(this.siteId);
//		return (this.siteId);
//	}
//}

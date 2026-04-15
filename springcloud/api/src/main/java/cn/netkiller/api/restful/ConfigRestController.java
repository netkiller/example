package cn.netkiller.api.restful;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/config")
public class ConfigRestController {
	
	@Value("${cms.api.url:Hello default}")
	private String message;

	@RequestMapping("/test")
	public String ping() {
		System.out.println(this.message);
		return (this.message);
	}
}

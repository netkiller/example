package cn.netkiller.api.restful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonRestController {
	private final Logger logger = LoggerFactory.getLogger(CommonRestController.class);

	@RequestMapping("/ping")
	public String ping() {
		logger.debug("PONG");
		return ("PONG");
	}

}

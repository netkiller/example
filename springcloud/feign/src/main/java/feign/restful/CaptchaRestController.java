package feign.restful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.common.feign.Captcha;
import cn.netkiller.common.pojo.ResponseRestful;

@RestController
@RequestMapping("/captcha")
public class CaptchaRestController {
	private static final Logger logger = LoggerFactory.getLogger(CaptchaRestController.class);
	@Autowired
	private Captcha captcha;

	@RequestMapping("/sms/{mobile}")
	public ResponseRestful sms(@PathVariable("mobile") String mobile) {
		// ResponseRestful rr = captcha.sms(mobile);
		logger.debug("Send SMS: {}", mobile);
		return captcha.sms(mobile);
	}

	@RequestMapping("/sms/{mobile}/{code}")
	public ResponseRestful sms(@PathVariable("mobile") String mobile, @PathVariable("code") String code) {
		return captcha.sms(mobile, code);
	}

	@RequestMapping("/sms/code/{mobile}")
	public String smsCode(@PathVariable("mobile") String mobile) {
		return captcha.smsCode(mobile);
	}
}

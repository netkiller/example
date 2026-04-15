package cn.netkiller.common.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.netkiller.common.pojo.ResponseRestful;

@FeignClient("restful-api-service")
public interface Captcha {
	@RequestMapping("/captcha/sms/{mobile}")
	public ResponseRestful sms(@PathVariable("mobile") String mobile);

	@RequestMapping("/captcha/sms/{mobile}/{code}")
	public ResponseRestful sms(@PathVariable("mobile") String mobile, @PathVariable("code") String code);

	@RequestMapping("/captcha/sms/code/{mobile}")
	public String smsCode(@PathVariable("mobile") String mobile);
}

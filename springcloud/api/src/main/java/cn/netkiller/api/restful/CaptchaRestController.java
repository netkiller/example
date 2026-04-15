package cn.netkiller.api.restful;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.common.pojo.ResponseRestful;
import cn.netkiller.common.service.AccessControlList;

@RestController
@RequestMapping("/captcha")
public class CaptchaRestController {
	private static final Logger logger = LoggerFactory.getLogger(CaptchaRestController.class);

	@Value("${sms.appid}")
	private int appid;

	@Value("${sms.appkey}")
	private String appkey;

	@Value("${sms.templateid}")
	private int templateid;

	@Autowired
	private AccessControlList accessControlList;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	private String cacheKeyPrefix = "restful:captcha:sms";
	private long expireTime = 600;

	// 发送验证码
	@RequestMapping("/sms/{mobile}")
	public ResponseRestful sms(@PathVariable String mobile) {

		if (accessControlList.isIpaddrBlocked(60, 10)) {
			// throw new RuntimeException("IP受限，发送过于频繁，请稍后再试");
			return new ResponseRestful(false, 10, "IP受限，发送过于频繁，请稍后再试", null);
		}
		if (accessControlList.isMobileBlocked(mobile, 60, 5)) {
			// throw new RuntimeException("手机号码受限，发送过于频繁，请稍后再试");
			return new ResponseRestful(false, 20, "手机号码受限，发送过于频繁，请稍后再试", null);
		}

		String cacheKey = String.format("%s:%s", cacheKeyPrefix, mobile);
		String code = this.getRandomCode();
		try {
			// SmsSingleSender singleSender = new SmsSingleSender(appid, appkey);
			// SmsSingleSenderResult singleSenderResult;

			ArrayList<String> params = new ArrayList<String>();
			params.add(code);
			params.add("1");
			// singleSenderResult = singleSender.sendWithParam("86", mobile, templateid, params, "", "", "");

			redisTemplate.opsForValue().set(cacheKey, code);
			redisTemplate.expire(cacheKey, expireTime, TimeUnit.SECONDS);
			// logger.debug(singleSenderResult.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseRestful(false, 30, e.getMessage(), null);
		}
		return new ResponseRestful(true, 1, "手机验证码发送成功", null);
	}

	// 校验验证码
	@RequestMapping("/sms/{mobile}/{code}")
	public ResponseRestful sms(@PathVariable("mobile") String mobile, @PathVariable("code") String code) {

		String cacheKey = String.format("%s:%s", cacheKeyPrefix, mobile);
		if (redisTemplate.hasKey(cacheKey)) {
			String cacheValue = redisTemplate.opsForValue().get(cacheKey);
			if (cacheValue.equals(code)) {
				redisTemplate.delete(cacheKey);
				return new ResponseRestful(true, 1, "验证码校验成功", null);
			}
		}
		return new ResponseRestful(false, 0, "验证码校验失败", null);
	}

	// 获取验证码
	@RequestMapping("/sms/code/{mobile}")
	public String smsCode(@PathVariable("mobile") String mobile) {

		String cacheKey = String.format("%s:%s", cacheKeyPrefix, mobile);
		if (redisTemplate.hasKey(cacheKey)) {
			String cacheValue = redisTemplate.opsForValue().get(cacheKey);
			return cacheValue;
		}
		return null;
	}

	// 四维随机验证码
	private String getRandomCode() {
		return String.valueOf((new Random().nextInt(8999) + 1000));
	}
}
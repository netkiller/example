package cn.netkiller.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.netkiller.common.service.AccessControlList;

@Service
public class AccessControlListImpl implements AccessControlList {
	private final Logger logger = LoggerFactory.getLogger(AccessControlList.class);
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	private String cacheKeyPrefix = "restful:acl";
	// private long expireTime = 60;

	public List<String> mobileWhiteList = new ArrayList<String>();
	public List<String> ipaddrWhiteList = new ArrayList<String>();

	public AccessControlListImpl() {
		mobileWhiteList.add("13113668890");
		mobileWhiteList.add("15219470825");
		ipaddrWhiteList.add("127.16.0.22");
	}

	public boolean isMobileBlocked(String mobile, long interval, int attempts) {
		if (mobileWhiteList.contains(mobile)) {
			logger.info("isMobileBlocked {} mobileWhiteList", mobile);
			return (false);
		}
		String key = String.format("%s:counter:mobile:%s", cacheKeyPrefix, mobile);
		if (this.aclCounter(key, interval, attempts) > attempts) {
			logger.info("isMobileBlocked {} blocked", mobile);
			return (true);
		}
		return (false);
	}

	public boolean isIpaddrBlocked(long interval, int attempts) {
		String ipAddr = request.getRemoteAddr();
		if (ipaddrWhiteList.contains(ipAddr)) {
			logger.info("isIpaddrBlocked {} ipaddrWhiteList", ipAddr);
			return (false);
		}
		String key = String.format("%s:counter:ipaddr:%s", cacheKeyPrefix, ipAddr);
		if (this.aclCounter(key, interval, attempts) > attempts) {
			logger.info("isIpaddrBlocked {} blocked", ipAddr);
			return (true);
		}
		return (false);
	}

	private int aclCounter(String key, long interval, int attempts) {
		int counter = 1;
		if(redisTemplate.getExpire(key) == -1) {
			redisTemplate.delete(key);
		}
		if (redisTemplate.hasKey(key)) {
			counter = Integer.valueOf(redisTemplate.opsForValue().get(key));
			if (counter > attempts)
				return counter;
			counter++;
			redisTemplate.opsForValue().set(key, String.valueOf(counter));
		} else {
			redisTemplate.opsForValue().set(key, "1");
			redisTemplate.expire(key, interval, TimeUnit.SECONDS);
		}
		logger.info("{}: {} TTL {}", key, counter, redisTemplate.getExpire(key));
		return counter;
	}
}

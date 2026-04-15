package cn.netkiller.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import cn.netkiller.common.domain.Member;
import cn.netkiller.common.pojo.ResponseRestful;
import cn.netkiller.common.repository.MemberRepository;
import cn.netkiller.common.service.AccessControlList;
import cn.netkiller.common.service.MemberService;

import javax.servlet.http.HttpServletRequest;

@Component
public class MemberServiceImpl implements MemberService {
	private final Logger logger = LoggerFactory.getLogger(MemberService.class);
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private AccessControlList accessControlList;

	public ResponseRestful sms(String mobile, String code) {
		if (accessControlList.isIpaddrBlocked(60, 10)) {
			logger.warn("sms IP地址访问过于频繁");
			return new ResponseRestful(false, 5, "IP地址访问过于频繁", null);
		}
		if (accessControlList.isMobileBlocked(mobile, 60, 5)) {
			logger.warn("sms 登录过于频繁，请稍后再试");
			return new ResponseRestful(false, 4, "登录过于频繁，请稍后再试", null);
		}
		String cacheKey = String.format("%s:%s", "restful:captcha:sms", mobile);
		if (redisTemplate.hasKey(cacheKey)) {
			String cacheValue = redisTemplate.opsForValue().get(cacheKey);
			if (cacheValue.equals(code)) {
				redisTemplate.delete(cacheKey);
				Member member = memberRepository.findByMobile(mobile);
				if (member == null) {
					member = new Member();
					member.setMobile(mobile);
					member.setIpAddress(request.getRemoteAddr());
					memberRepository.save(member);
					return new ResponseRestful(true, 1, "首次登录成功", member);
				}
				return new ResponseRestful(true, 1, "登录成功", member);
			} else {
				return new ResponseRestful(false, 2, "登录失败，验证码错误", null);
			}
		} else {
			return new ResponseRestful(false, 3, "登录失败，验证码过期", null);
		}
	}

	public ResponseRestful bindWechat(Member wechatMember) {
		if (wechatMember.getMobile() == null) {
			return new ResponseRestful(false, 1, "手机号码不存在", wechatMember);
		}
		Member member = memberRepository.findByMobile(wechatMember.getMobile());
		if (member == null) {
			return new ResponseRestful(false, 1, "微信绑定失败，手机号码错误", wechatMember);
		} else {
			member.setName(wechatMember.getName());
//			member.setMobile(wechatMember.getMobile());
			member.setWechat(wechatMember.getWechat());
			member.setPicture(wechatMember.getPicture());
			member.setIpAddress(wechatMember.getIpAddress());
			return new ResponseRestful(true, 1, "微信绑定成功", memberRepository.save(member));
		}
	}

	public ResponseRestful bindMobile(String wechat, String mobile) {
		Member member = memberRepository.findByWechat(wechat);
		if (member == null) {
			return new ResponseRestful(true, 0, "手机绑定失败", null);
		}
		member.setMobile(mobile);
		memberRepository.save(member);
		return new ResponseRestful(true, 1, "手机绑定成功", member);
	}

	public ResponseRestful update(int id, Member newMember) {
		if (id < 1) {
			// throw new RuntimeException("用户不存在");
			new ResponseRestful(false, 0, "用户资料更新失败", newMember);
		}
		Member member = memberRepository.findById(id);
		if (member != null) {
			logger.info("update {} => {}", member, newMember);
			if (newMember.getMobile() != null)
				member.setMobile(newMember.getMobile());
			if (newMember.getAge() != 0)
				member.setAge(newMember.getAge());
			if (newMember.getName() != null)
				member.setName(newMember.getName());
			if (newMember.getWechat() != null)
				member.setWechat(newMember.getWechat());
			if (newMember.getSex() != null)
				member.setSex(newMember.getSex());
			if (newMember.getPicture() != null) {
				member.setPicture(newMember.getPicture());
			}
			return new ResponseRestful(true, 1, "用户资料更新成功", memberRepository.save(member));
		} else {
			return new ResponseRestful(true, 0, "用户资料更新失败，没有找到响应的用户", null);
		}
	}

	public ResponseRestful loginWechat(String wechat) {
		Member member = memberRepository.findByWechat(wechat);
		if (member == null) {
			return new ResponseRestful(false, 0, "微信登陆失败", null);
		}
		return new ResponseRestful(true, 1, "微信登陆成功", member);
	}
}

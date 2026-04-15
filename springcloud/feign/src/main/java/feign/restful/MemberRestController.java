package feign.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.common.domain.Member;
import cn.netkiller.common.feign.MemberFeignClient;
import cn.netkiller.common.pojo.ResponseRestful;

@RestController
public class MemberRestController {

	@Autowired
	private MemberFeignClient memberFeignClient;

	/**
	 * 创建会员
	 * @param member
	 * @return
	 */
	@RequestMapping(value = "/member/create", method = RequestMethod.POST)
	public ResponseEntity<Member> create(@RequestBody Member member) {
		return memberFeignClient.create(member);
	}

	/**
	 * 更新会员信息
	 * @param id
	 * @param newMember
	 * @return
	 */
	@RequestMapping(value = "/member/update/{id}", method = RequestMethod.POST)
	public ResponseEntity<ResponseRestful> update(@PathVariable("id") int id, @RequestBody Member newMember) {
		return memberFeignClient.update(id, newMember);
	}

	/**
	 * 获取会员信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/member/get/{id}")
	public Member get(@PathVariable("id") int id) {
		return memberFeignClient.get(id);
	}
	
	@RequestMapping("/member/login/mobile/{mobile}")
	public Member loginMobile(@PathVariable("mobile") String mobile) {
		return memberFeignClient.loginMobile(mobile);
	}

	@RequestMapping(value = "/member/login/wechat/{wechat}", method = RequestMethod.GET)
	public ResponseRestful loginWechat(@PathVariable("wechat") String wechat) {
		return memberFeignClient.loginWechat(wechat);
	}

	/**
	 *  验证短信验证码，同时登陆
	 * @param mobile
	 * @param code
	 * @return
	 */
	@RequestMapping("/member/login/sms/{mobile}/{code}")
	public ResponseRestful sms(@PathVariable("mobile") String mobile, @PathVariable("code") String code) {
		return memberFeignClient.sms(mobile, code);
	}

	@RequestMapping(value = "/member/bind/wechat", method = RequestMethod.POST)
	public ResponseRestful bindWechat(@RequestBody Member member) {
		return memberFeignClient.bindWechat(member);
	}

	@RequestMapping("/member/bind/mobile/{wechat}/{mobile}")
	public ResponseRestful bindMobile(@PathVariable("wechat") String wechat, @PathVariable("mobile") String mobile) {
		return memberFeignClient.bindMobile(wechat, mobile);
	}

}

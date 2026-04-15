package cn.netkiller.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.netkiller.common.domain.Member;
import cn.netkiller.common.pojo.ResponseRestful;

@FeignClient("restful-api-service")
public interface MemberFeignClient {
	@RequestMapping(value = "/member/create", method = RequestMethod.POST)
	public ResponseEntity<Member> create(@RequestBody Member member);

	@RequestMapping(value = "/member/update/{id}", method = RequestMethod.POST)
	public ResponseEntity<ResponseRestful> update(@PathVariable("id") int id, @RequestBody Member newMember);

	@RequestMapping("/member/get/{id}")
	public Member get(@PathVariable("id") int id);

	@RequestMapping("/member/login/mobile/{mobile}")
	public Member loginMobile(@PathVariable("mobile") String mobile);

	@RequestMapping(value = "/member/login/wechat/{wechat}", method = RequestMethod.GET)
	public ResponseRestful loginWechat(@PathVariable("wechat") String wechat);

	@RequestMapping("/member/login/sms/{mobile}/{code}")
	public ResponseRestful sms(@PathVariable("mobile") String mobile, @PathVariable("code") String code);

	@RequestMapping(value = "/member/bind/wechat", method = RequestMethod.POST)
	public ResponseRestful bindWechat(@RequestBody Member member);

	@RequestMapping("/member/bind/mobile/{wechat}/{mobile}")
	public ResponseRestful bindMobile(@PathVariable("wechat") String wechat, @PathVariable("mobile") String mobile);
}

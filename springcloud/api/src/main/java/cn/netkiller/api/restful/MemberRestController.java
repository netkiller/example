package cn.netkiller.api.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.common.domain.Member;
import cn.netkiller.common.pojo.ResponseRestful;
import cn.netkiller.common.repository.MemberRepository;
import cn.netkiller.common.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberRestController {
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = { "application/xml", "application/json" })
	public ResponseEntity<Member> create(@RequestBody Member member) {
		memberRepository.save(member);
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST, produces = { "application/xml", "application/json" })
	public ResponseEntity<ResponseRestful> update(@PathVariable("id") int id, @RequestBody Member newMember) {
		ResponseRestful restfulResponse = memberService.update(id, newMember);
		return new ResponseEntity<ResponseRestful>(restfulResponse, HttpStatus.OK);
	}

//	@RequestMapping("/get/{id}")
//	public Member get(@PathVariable("id") int id) {
//		Member member = memberRepository.findOne(id);
//		if (member == null) {
//			member = new Member();
//		}
//		return member;
//	}

	@RequestMapping("/login/mobile/{mobile}")
	public Member loginMobile(@PathVariable("mobile") String mobile) {
		Member member = memberRepository.findByMobile(mobile);
		if (member == null) {
			member = new Member();
		}
		return member;
	}

	@RequestMapping(value = "/login/wechat/{wechat}", method = RequestMethod.GET)
	public ResponseRestful loginWechat(@PathVariable("wechat") String wechat) {
		return memberService.loginWechat(wechat);
	}

	@RequestMapping("/login/sms/{mobile}/{code}")
	public ResponseRestful sms(@PathVariable("mobile") String mobile, @PathVariable("code") String code) {
		return memberService.sms(mobile, code);
	}

	@RequestMapping(value = "/bind/wechat", method = RequestMethod.POST)
	public ResponseRestful bindWechat(@RequestBody Member member) {
		return memberService.bindWechat(member);
	}

	@RequestMapping("/bind/mobile/{wechat}/{mobile}")
	public ResponseRestful bindMobile(@PathVariable("wechat") String wechat, @PathVariable("mobile") String mobile) {
		return memberService.bindMobile(wechat, mobile);
	}
}

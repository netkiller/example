package cn.netkiller.common.service;

import cn.netkiller.common.domain.Member;
import cn.netkiller.common.pojo.ResponseRestful;

public interface MemberService {

	ResponseRestful sms(String mobile, String code);

	ResponseRestful bindMobile(String wechat, String mobile);

	ResponseRestful update(int i, Member newMember);

	ResponseRestful loginWechat(String wechat);

	ResponseRestful bindWechat(Member member);

}

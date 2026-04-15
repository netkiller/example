package cn.netkiller.sms;

import cn.netkiller.autoconfigure.SmsProperties;

public class SmsSender {

	public SmsProperties smsProperties;

	public SmsSender(SmsProperties smsProperties) {
		this.smsProperties = smsProperties;
	}

	public boolean send(String message) {
		System.out.println(this.smsProperties.toString());
		System.out.println(message);
		return true;
	};

}

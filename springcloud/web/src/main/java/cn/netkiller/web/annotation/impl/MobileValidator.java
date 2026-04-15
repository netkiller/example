package cn.netkiller.web.annotation.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import cn.netkiller.web.annotation.Mobile;

public class MobileValidator implements ConstraintValidator<Mobile, String> {

	public MobileValidator() {
		// TODO Auto-generated constructor stub
	}

	private boolean required = false;

	@Override
	public void initialize(Mobile constraintAnnotation) {
		required = constraintAnnotation.isRequired();
	}

	@Override
	public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
		Pattern mobile_pattern = Pattern.compile("1\\d{10}");
		// System.out.println(phone);
		// 是否为手机号的实现
		if (required) {
			if (StringUtils.isEmpty(phone)) {
				return false;
			}
			Matcher m = mobile_pattern.matcher(phone);
			return m.matches();

		} else {
			return StringUtils.isEmpty(phone);
		}
	}
}

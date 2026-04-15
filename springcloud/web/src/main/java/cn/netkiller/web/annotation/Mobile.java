package cn.netkiller.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

import javax.validation.Constraint;
import javax.validation.Payload;

import cn.netkiller.web.annotation.impl.MobileValidator;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MobileValidator.class)
@Documented
// 注解的实现类。
public @interface Mobile {
	// 校验错误的默认信息
	String message() default "手机号码格式不正确！";

	// 是否强制校验
	boolean isRequired() default true;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

//package cn.netkiller.log;
//
//import java.lang.reflect.Method;
//import java.util.Objects;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Component
//@Aspect
//public class ControllerMethodLogAspect {
//
//	@Pointcut("@annotation(ControllerMethodLog)")
//	public void pointCut() {
//	}
//
//	/**
//	 * 在切点运行前执行该方法
//	 */
//	@Before("pointCut()")
//	public void doBefore(JoinPoint joinPoint) {
//		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//		Method method = signature.getMethod();
//		ControllerMethodLog annotation = method.getAnnotation(ControllerMethodLog.class);
//		if (Objects.isNull(annotation)) {
//			return;
//		}
//		String methodName = method.getDeclaringClass().getSimpleName() + "." + method.getName();
//		log.info("start {}：入参：{}", methodName, joinPoint.getArgs().toString());
//	}
//
//	/**
//	 * 在切点运行后,无异常时执行该方法
//	 *
//	 * @param joinPoint
//	 * @param result
//	 */
//	@AfterReturning(value = "pointCut()", returning = "result")
//	public void afterReturn(JoinPoint joinPoint, Object result) {
//		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//		Method method = signature.getMethod();
//		ControllerMethodLog annotation = method.getAnnotation(ControllerMethodLog.class);
//		if (Objects.isNull(annotation)) {
//			return;
//		}
//		String methodName = method.getDeclaringClass().getSimpleName() + "." + method.getName();
//		log.info("end {}：响应：{}", methodName, result.toString());
//	}
//
//}

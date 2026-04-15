package cn.netkiller.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmployeeServiceAspect {
	public EmployeeServiceAspect() {
	}

	@Before(value = "execution(* cn.netkiller.aop.service.EmployeeService.*(..)) and args(id, name)")
	public void beforeAdvice(JoinPoint joinPoint, String id, String name) {
		System.out.println("Before method:" + joinPoint.getSignature());

		System.out.println("Creating Employee with id: " + id + ", name: " + name);
	}

	@After(value = "execution(* cn.netkiller.aop.service.EmployeeService.*(..)) and args(id,name)")
	public void afterAdvice(JoinPoint joinPoint, String id, String name) {
		System.out.println("After method:" + joinPoint.getSignature());

		System.out.println("Successfully created Employee with id: " + id + ", name: " + name);
	}
}
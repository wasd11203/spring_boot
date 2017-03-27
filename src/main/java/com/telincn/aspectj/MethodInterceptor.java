package com.telincn.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 拦截器
 * @author ganzhigang
 * 时间：2017年3月24日 下午4:16:56
 */
@Aspect
@Configuration
public class MethodInterceptor {
	
	@Pointcut("execution(* com.telincn.controller..*(..) )")
	public void methodPointCut(){
		
	}
	
	@Around("methodPointCut()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Aspectj ... ... :"+auth.getAuthorities());
		
		return pjp.proceed();
	}
}

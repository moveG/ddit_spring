package com.spring.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//어노테이션 방식 : 결합도가 너무 높아 사용 비추
//@Component
//@Aspect
public class AdviceBehavior {

	//@Before("execution(public * com.spring.aspects..잠자기*(..))")
	public void chikachika() {
		System.out.println("열심히 양치질을 합니다.");
	}

	//joinpoint : around
	public void chikachikaAround(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("데이트 전 닦기");

		joinPoint.proceed();

		System.out.println("데이트 후 닦기");
	}
}

package cn.edu.ujn.lab1.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	// 定义切入点表达式
	@Pointcut("execution(* cn.edu.ujn.lab1.*.*.*(..))")
	private void myPointCut() {
	}

	// 环绕通知
	@Around("myPointCut()")
	public Object myAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("环绕通知开始：方法开始执行..." + proceedingJoinPoint.getSignature().getName());
		Object obj = proceedingJoinPoint.proceed();
		System.out.println("环绕通知结束：方法执行结束..." + proceedingJoinPoint.getSignature().getName());
		return obj;
	}

	// 异常通知
	@AfterThrowing(value = "myPointCut()", throwing = "e")
	public void myAfterThrowing(JoinPoint joinPoint, Throwable e) {
		System.out.println("异常通知：" + "出错了" + e.getMessage());
	}

}

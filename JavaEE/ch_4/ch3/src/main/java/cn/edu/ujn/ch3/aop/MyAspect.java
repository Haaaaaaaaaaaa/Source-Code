package cn.edu.ujn.ch3.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class MyAspect {
//	第一个*代表返回值任意
//	有一个空格
//	扫描cn.edu.ujn.ch3.aop这个包
//	.*.*任意类任意方法
//	..参数任意
	@Pointcut("execution(* cn.edu.ujn.ch3.aop.*.*(..) )") 
	private void myPointCut() {	
	}
	
	@Before("myPointCut()")
	public void myBefore(JoinPoint jp){
		System.out.println("前置通知：方法开始执行...."+jp.getSignature().getName());		
	}
}

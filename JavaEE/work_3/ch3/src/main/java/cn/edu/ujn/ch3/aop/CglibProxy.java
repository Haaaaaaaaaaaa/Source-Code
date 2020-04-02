package cn.edu.ujn.ch3.aop;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("日志输出：cglib.....方法执行........"+method);
		Object target = proxy.invokeSuper(obj, args);
		System.out.println("日志输出：cglib......方法结束.......");
		return target;
	}

}

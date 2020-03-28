package cn.edu.ujn.ch2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	IUserService uservice = (IUserService) context.getBean("userService");
	IUserService uservice2 = (IUserService) context.getBean("userService");
	uservice.save();
//	==对比的是两个对象在内存中的地址
	if(uservice==uservice2) 
		System.out.println("单例");
	else
		System.out.println("原型");
	System.out.println(uservice);
	System.out.println(uservice2);
	
}
}


package cn.edu.ujn.ch1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestIoC {

	public static void main(String[] args) {
//		创建Spring的上下文
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IUserService userService = (IUserService) context.getBean("userService");
		userService.say();
	}
}

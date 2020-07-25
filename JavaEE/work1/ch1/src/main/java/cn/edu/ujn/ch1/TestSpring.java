package cn.edu.ujn.ch1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
//	mian alt+/
	public static void main(String[] args) {
//		CPXAC alt+/
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//		使用ID获取到
		IUserDao userdao = (IUserDao) ctx.getBean("userDao");
		userdao.say();
	}
}

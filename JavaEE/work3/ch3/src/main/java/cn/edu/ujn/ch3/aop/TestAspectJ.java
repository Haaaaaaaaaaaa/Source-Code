package cn.edu.ujn.ch3.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAspectJ {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IUserDao userdao = (IUserDao) context.getBean("userDao");
		userdao.addUser();
		userdao.deleteUser();
	}
}

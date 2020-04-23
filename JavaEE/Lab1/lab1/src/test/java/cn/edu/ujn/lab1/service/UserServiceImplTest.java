package cn.edu.ujn.lab1.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.ujn.lab1.model.Student;
import cn.edu.ujn.lab1.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceImplTest {
	@Autowired
	private User user;
	@Autowired
	private Student stu;
	@Autowired
	private IUserService userService;

	@Test
	public void registerTest() {
		user.setUsername("ct");
		user.setPassword("123456");
		
		stu.setStuname("ct");
		stu.setStunumber("202002");
		
		User register = this.userService.register(user, stu);
		System.out.println("register");
	}
}

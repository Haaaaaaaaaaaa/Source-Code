package cn.edu.ujn.reviewlab1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.ujn.reviewlab1.model.Student;
import cn.edu.ujn.reviewlab1.model.User;
import cn.edu.ujn.reviewlab1.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserTest {
	@Autowired
	private IUserService userService;
	@Autowired
	private User user;
	@Autowired
	private Student stu;

	@Test
	public void addUserTest() {
		user.setUsername("888");
		user.setPassword("9999");
		stu.setStuname("fuxi");
		stu.setStunumber("111");
		User addUser = this.userService.addUser(user, stu);
		System.out.println("成功");
	}
}

package cn.edu.ujn.lab1.service;

import java.util.List;

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

//	测试添加（注册）
	@Test
	public void registerTest() {
		user.setUsername("ct");
		user.setPassword("123456");

		stu.setStuname("ct");
		stu.setStunumber("202002");

		User register = this.userService.register(user, stu);
		System.out.println("register");
	}

//	测试查询所有用户信息
	@Test
	public void findAllUserTest() {
		List<User> user = this.userService.findAllUser();
		// 循环输出集合中的对象
		for (User us : user) {
			System.out.println(us);
		}
	}

//	测试修改用户信息
	@Test
	public void updateUserTest() {
		User user = new User();
		user.setUid(19);
		user.setUsername("ct19");
		user.setPassword("12345678");

		int num = this.userService.updateUser(user);
		if (num > 0) {
			System.out.println("成功修改了" + num + "条数据！");
		} else {
			System.out.println("修改失败！");
		}
	}
//	测试删除用户信息
	@Test
	public void deleteUsertTest() {
		int num = this.userService.deleteUser(18);
		if (num > 0) {
			System.out.println("成功删除了" + num + "条数据！");
		} else {
			System.out.println("删除操作执行失败！");
		}
	}
}

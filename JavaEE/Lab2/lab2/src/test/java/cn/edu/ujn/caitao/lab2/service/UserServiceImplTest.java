package cn.edu.ujn.caitao.lab2.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.ujn.caitao.lab2.dao.Orders;
import cn.edu.ujn.caitao.lab2.dao.Product;
import cn.edu.ujn.caitao.lab2.dao.User;
import cn.edu.ujn.caitao.lab2.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class UserServiceImplTest {

	@Autowired
	private IUserService userService;

	// 查询用户，并将用户的订单输出
	@Test
	public void selectByPrimaryKeyTest() {
		User user = this.userService.selectByPrimaryKey(5);
		System.out.println("用户名："+user.getName()+",密码:"+user.getPassword());
		for (Orders order : user.getOrdersList()) {
			System.out.println("订单号："+order.getNumber());
			System.out.println("包含的商品:");
			for (Product prod : order.getProductList()) {
				System.out.println("商品名:"+prod.getName()+",商品价格:"+prod.getPrice());
			}
		}
	}

	// 查询所用用户的测试
	@Test
	public void findAllUserTest() {
		List<User> allUser = this.userService.findAllUser();
		for (User user : allUser) {
			System.out.println(user);
		}

	}

	// 添加用户的测试
	@Test
	public void insertTest() {
		User user = new User();
		user.setName("蔡涛");
		user.setPassword("123456");
		int i = this.userService.insert(user);
		System.out.println("成功添加了" + i + "条信息！");
	}

	// 删除用户的测试
	@Test
	public void deleteByPrimaryKey() {
		int i = this.userService.deleteByPrimaryKey(8);
		System.out.println("成功删除了" + i + "个用户信息！");
	}

	// 更新用户的测试
	@Test
	public void updateByPrimaryKey() {
		User user = new User();
		user.setId(6);
		user.setName("蔡涛");
		int i = this.userService.updateByPrimaryKey(user);
		System.out.println("更新了" + i + "......");
	}

}

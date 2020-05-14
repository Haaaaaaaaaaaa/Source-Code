package cn.edu.ujn.ch9;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import cn.edu.ujn.ch9.dao.Orders;
import cn.edu.ujn.ch9.dao.Person;
import cn.edu.ujn.ch9.dao.User;
import cn.edu.ujn.ch9.util.MyBatisUtils;

public class TestMyBatis {
	/* 测试一对一，一个用户有一个身份证号 */
	@Test
	public void findPersonByIdTest() {
		// 1、通过工具类生成SqlSession对象
		SqlSession session = MyBatisUtils.getSession();
		// 2.使用MyBatis嵌套查询的方式查询id为1的人的信息
		Person person = session.selectOne("cn.edu.ujn.ch9.dao.PersonMapper.selectById", 1);
		// 3、输出查询结果信息
		System.out.println(person);
		// 4、关闭SqlSession
		session.close();
	}

	/* 测试一对多，一个用户有多个订单 */
	@Test
	public void findUserTest() {
		// 1、通过工具类生成SqlSession对象
		SqlSession session = MyBatisUtils.getSession();
		// 2、查询id为1的用户信息
		User user = session.selectOne("cn.edu.ujn.ch9.dao.UserMapper.selectByPrimaryKey", 1);
		// 3、输出查询结果信息
		System.out.println(user);
		// 4、关闭SqlSession
		session.close();
	}

	/* 测试多对多，一个订单有多个商品 */
	@Test
	public void findOrdersTest() {
		// 1、通过工具类生成SqlSession对象
		SqlSession session = MyBatisUtils.getSession();
		// 2、查询id为1的订单中的商品信息
		Orders orders = session.selectOne("cn.edu.ujn.ch9.dao.OrdersMapper.selectByPrimaryKey", 1);
		// 3、输出查询结果信息
		System.out.println(orders);
		// 4、关闭SqlSession
		session.close();
	}

}

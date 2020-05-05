package cn.edu.ujn.ch7;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import cn.edu.ujn.ch7.dao.Customer;
import cn.edu.ujn.ch7.utils.MyBatisUtils;

public class MyBatisTest {
	@Test
	public void selectCustomer() {
		SqlSession session = MyBatisUtils.getSession();
		Customer customer = session.selectOne("cn.edu.ujn.ch7.dao.CustomerMapper.selectByPrimaryKey", 1);
		System.out.println(customer);
		session.close();
	}
	@Test
	public void insertTest() {
		Customer cc = new Customer();
		cc.setUsername("ct");
		cc.setJobs("student");
		cc.setPhone("666");
		
		SqlSession session=MyBatisUtils.getSession();
		session.insert("cn.edu.ujn.ch7.dao.CustomerMapper.insert",cc);
		session.commit();
		System.out.println(cc);
		session.close();
	}
}

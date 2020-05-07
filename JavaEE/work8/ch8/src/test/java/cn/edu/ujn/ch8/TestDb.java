package cn.edu.ujn.ch8;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import cn.edu.ujn.ch8.dao.Customer;
import cn.edu.ujn.ch8.utils.MybatisUtils;

public class TestDb {
	@Test
	public void insertCustomerTest() {
		SqlSession session = MybatisUtils.getSession();
		Customer cc = new Customer();
		cc.setUsername("jingshan");
		cc.setJobs("teacher");
		cc.setPhone("666666");
		int insert = session.insert("cn.edu.ujn.ch8.dao.CustomerMapper.insertSelective", cc);
		session.commit();
		System.out.println(insert + "," + cc);
		session.close();
	}

	@Test
	public void findByNameAndJobsTest() {
		Customer customer = new Customer();
		customer.setUsername("j");
		customer.setJobs("teacher");
		SqlSession session = MybatisUtils.getSession();
		List<Customer> list = session.selectList("cn.edu.ujn.ch8.dao.CustomerMapper.findByNameAndJobs", customer);
		for (Customer cc : list) {
			System.out.println(cc);
		}
		session.close();
	}

	@Test
	public void findByNameOrJobsTest() {
		Customer customer = new Customer();
		customer.setUsername("j");
		customer.setJobs("teacher");
		SqlSession session = MybatisUtils.getSession();
		List<Customer> list = session.selectList("cn.edu.ujn.ch8.dao.CustomerMapper.findByNameOrJobs", customer);
		for (Customer cc : list) {
			System.out.println(cc);
		}
		session.close();
	}

	@Test
	public void findByIdTest() {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(2);
		ids.add(7);
		
		SqlSession session = MybatisUtils.getSession();
		List<Customer> list = session.selectList("cn.edu.ujn.ch8.dao.CustomerMapper.findByIds", ids);
		for(Customer cc:list) {
			System.out.println(cc);
		}
		session.close();
	}
}

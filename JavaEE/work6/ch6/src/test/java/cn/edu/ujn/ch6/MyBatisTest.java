package cn.edu.ujn.ch6;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.edu.ujn.ch6.dao.Customer;
import cn.edu.ujn.ch6.dao.CustomerMapper;

public class MyBatisTest {
//	代码公用，进行重构，提取成一个方法
	private SqlSession getSession() throws IOException {
		String res = "mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(res);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = sessionFactory.openSession();
		return session;
	}

	@Test
	public void findById() throws IOException {
		SqlSession session = getSession();
		Customer cust = session.selectOne("cn.edu.ujn.ch6.dao.CustomerMapper.selectByPrimaryKey", 1);
		System.out.println(cust);
		session.close();
	}

	@Test
	public void fiandByname() throws IOException {
		SqlSession session = getSession();
		List<Customer> list = session.selectList("cn.edu.ujn.ch6.dao.CustomerMapper.selsctByName", "j");
		System.out.println(list);
		session.close();
	}

	@Test
	public void insert() throws IOException {
		SqlSession session = getSession();
		Customer c = new Customer();
		c.setUsername("ct1");
		c.setJobs("teacher");
		c.setPhone("17860630030");
		int insert = session.insert("cn.edu.ujn.ch6.dao.CustomerMapper.insertSelective", c);
		System.out.println(insert);
		session.commit();
		session.close();
	}

//	使用CustomerMapper这个接口实现查询
	@Test
	public void findByIdTest() throws IOException {
		SqlSession session = getSession();
		CustomerMapper mapper = session.getMapper(CustomerMapper.class);
		Customer cc = mapper.selectByPrimaryKey(2);
		System.out.println(cc);
		session.close();
	}

//	模糊查询
	@Test
	public void findCustomerByName() throws Exception {
		// 定义配置文件
		String resource = "mybatis-config.xml";
		// 加载
		InputStream is = Resources.getResourceAsStream(resource);
		// 构造会话工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		// 构造会话对象
		SqlSession session = sqlSessionFactory.openSession();
		// 执行查询
		List<Customer> list = session.selectList("cn.edu.ujn.ch6.dao.CustomerMapper.findCustomerByName", "c");
		// 遍历输出
		for (Customer cc : list) {
			System.out.println(cc);
		}
		// 关闭session
		session.close();
	}
}

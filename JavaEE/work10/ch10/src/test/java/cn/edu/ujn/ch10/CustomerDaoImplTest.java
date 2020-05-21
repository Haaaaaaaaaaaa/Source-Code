package cn.edu.ujn.ch10;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.ujn.ch10.dao.Customer;
import cn.edu.ujn.ch10.dao.CustomerMapper;
import cn.edu.ujn.ch10.dao.ICustomerDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class CustomerDaoImplTest {
	// 这个变量名不可以随便取
	// 写customerDao会出错。
	@Autowired
	private ICustomerDao customerDaoImpl;
	@Autowired
	private CustomerMapper customerMapper;

	@Test
	public void addTest() {
		Customer customer = new Customer();
		customer.setUsername("蔡涛");
		customer.setJobs("学生");
		customer.setPhone("666");
		this.customerDaoImpl.add(customer);
		System.out.println("添加成功！");
	}

	@Test
	public void insertTset() {
		Customer customer = new Customer();
		customer.setUsername("律师");
		customer.setJobs("学生");
		customer.setPhone("888888");
		int insert = this.customerMapper.insert(customer);
		System.out.println("成功添加" + insert + "条数据");
	}
}

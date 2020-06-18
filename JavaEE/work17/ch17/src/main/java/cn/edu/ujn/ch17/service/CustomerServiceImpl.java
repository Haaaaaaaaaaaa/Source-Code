package cn.edu.ujn.ch17.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.ujn.ch17.dao.Customer;
import cn.edu.ujn.ch17.dao.CustomerMapper;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private CustomerMapper customerDao;

	@Override
	public Customer findCustomerById(Integer id) {
		// TODO Auto-generated method stub
		return this.customerDao.selectByPrimaryKey(id);
	}

	@Override
	public int addCustomer(Customer customer) {
		int insert = this.customerDao.insertSelective(customer);
		return insert;
	}

	@Override
	public List<Customer> findAllCustomer() {
		return this.customerDao.findAllCustomer();
	}
}

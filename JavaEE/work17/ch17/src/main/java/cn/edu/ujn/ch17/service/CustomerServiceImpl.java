package cn.edu.ujn.ch17.service;

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

}

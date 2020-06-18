package cn.edu.ujn.ch17.service;

import java.util.List;

import cn.edu.ujn.ch17.dao.Customer;

public interface ICustomerService {
//查询
	public Customer findCustomerById(Integer id);

//添加
	public int addCustomer(Customer customer);

//查询列表显示
	public List<Customer> findAllCustomer();

}

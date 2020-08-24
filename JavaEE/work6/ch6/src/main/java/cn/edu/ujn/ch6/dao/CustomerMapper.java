package cn.edu.ujn.ch6.dao;

import java.util.List;

import cn.edu.ujn.ch6.dao.Customer;

public interface CustomerMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Customer record);

	int insertSelective(Customer record);

	Customer selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Customer record);

	int updateByPrimaryKey(Customer record);

	// 依据客户名实现模糊查询
	List<Customer> findCustomerByName(String name);

}
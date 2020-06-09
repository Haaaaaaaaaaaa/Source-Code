package cn.edu.ujn.caitao.lab2.service;

import java.util.List;

import cn.edu.ujn.caitao.lab2.dao.Orders;

public interface IOrdersService {
	int insertAOrder(Orders record);					// 添加订单
	List<Orders> findOrdersByUsersId(int user_id);		// 用过用户查询订单
}

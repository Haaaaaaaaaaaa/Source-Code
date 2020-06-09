package cn.edu.ujn.caitao.lab2.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.ujn.caitao.lab2.dao.Orders;

public interface OrdersMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Orders record);
    int insertSelective(Orders record);
    Orders selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(Orders record);
    int updateByPrimaryKey(Orders record); 
    int addOrdersItem(Map orderItem);				// 向ordersitem表插入订单和商品的映射
    List<Orders> findOrdersWithUser(int id);			// 通过用户id查询订单
}
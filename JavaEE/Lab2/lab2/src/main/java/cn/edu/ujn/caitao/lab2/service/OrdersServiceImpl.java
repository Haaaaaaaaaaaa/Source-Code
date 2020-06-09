package cn.edu.ujn.caitao.lab2.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.ujn.caitao.lab2.dao.Orders;
import cn.edu.ujn.caitao.lab2.dao.OrdersMapper;
import cn.edu.ujn.caitao.lab2.dao.Product;

@Transactional
@Service("orderServiceImpl")
public class OrdersServiceImpl implements IOrdersService {

	@Autowired
	private OrdersMapper ordersMapper;
	
	// 添加订单的实现，需要对orders表和ordersitem表分别插入信息
	@Override
	public int insertAOrder(Orders record) {
		int idn = this.ordersMapper.insert(record);
		if(idn>=0) {
			List<Product> productList = record.getProductList();
			for (Product product : productList) {
				Map orderItem =  new HashMap();
				orderItem.put("orders_id", record.getId());
				orderItem.put("product_id", product.getId());
				int item = this.ordersMapper.addOrdersItem(orderItem);
				System.out.println("插入成功(orderItem):"+orderItem+"编号："+ orderItem.get("id"));
//				int i = 1/0;				// 认为的异常，用来测试事务
			}
			
		}
		return record.getId();
	}
	
	// 通过用户id查询订单
	@Override
	public List<Orders> findOrdersByUsersId(int user_id) {
		return  this.ordersMapper.findOrdersWithUser(user_id);
	}

}

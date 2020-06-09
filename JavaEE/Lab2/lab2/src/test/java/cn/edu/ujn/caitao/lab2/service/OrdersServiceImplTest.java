package cn.edu.ujn.caitao.lab2.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.ujn.caitao.lab2.dao.Orders;
import cn.edu.ujn.caitao.lab2.dao.Product;
import cn.edu.ujn.caitao.lab2.service.IOrdersService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class OrdersServiceImplTest {

	@Autowired
	private IOrdersService ordersServiceImpl;
	
	// 测试插入一个订单
	@Test
	public void insertOrderTest() {
		Orders order1 = new Orders();
		
		// 设置用户id
		order1.setUserId(5);
		
		// 设置订单编号
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = df.format(new Date());
		order1.setNumber(date);
		
		// 设置订单中的商品
		Product product1 = new Product();
		Product product2 = new Product();
		product1.setId(1);
		product2.setId(2);
		
		ArrayList<Product> list = new ArrayList<>();
		list.add(product1);
		list.add(product2);
		order1.setProductList(list);
		
		int i = ordersServiceImpl.insertAOrder(order1);
		System.out.println("插入了订单号："+i);
	}
	
	// 查询订单
	@Test
	public void findOrdersByUsersIdTest() {
		List<Orders> list = this.ordersServiceImpl.findOrdersByUsersId(1);
		for (Orders order : list) {
			System.out.println(order);
		}
	}
	
	
	
	
}

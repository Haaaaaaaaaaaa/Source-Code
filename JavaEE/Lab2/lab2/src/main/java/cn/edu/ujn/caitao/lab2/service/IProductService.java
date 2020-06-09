package cn.edu.ujn.caitao.lab2.service;

import java.util.List;

import cn.edu.ujn.caitao.lab2.dao.Product;

public interface IProductService {
	
	List<Product> findProductsByorder(int order_id);		// 通过订单id查询商品
}

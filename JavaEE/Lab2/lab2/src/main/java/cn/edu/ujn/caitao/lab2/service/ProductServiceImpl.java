package cn.edu.ujn.caitao.lab2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.ujn.caitao.lab2.dao.Product;
import cn.edu.ujn.caitao.lab2.dao.ProductMapper;

@Service("productService")
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductMapper productMapper;
	
	// 查询订单下的商品
	@Override
	public List<Product> findProductsByorder(int order_id) {
		 
		return  this.productMapper.findProductById(order_id);
	}

}

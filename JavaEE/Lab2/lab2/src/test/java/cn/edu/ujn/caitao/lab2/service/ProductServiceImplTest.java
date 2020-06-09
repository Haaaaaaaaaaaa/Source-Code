package cn.edu.ujn.caitao.lab2.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.ujn.caitao.lab2.dao.Product;
import cn.edu.ujn.caitao.lab2.dao.ProductMapper;
import cn.edu.ujn.caitao.lab2.service.IProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class ProductServiceImplTest {
	
	@Autowired
	private IProductService productService;
	
	// 通过订单号查询商品测试
	@Test
	public void findProductsByorderTest() {
		
		List<Product> list = this.productService.findProductsByorder(3);
		for (Product product : list) {
			System.out.println(product);
		}
		
		
		
	}
}

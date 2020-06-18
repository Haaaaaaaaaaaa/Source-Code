package cn.edu.ujn.cait037.service;

import java.util.List;

import cn.edu.ujn.cait037.dao.Info_product;

public interface IInfo_productService {
	// 查询
	public Info_product findProductById(Integer id);

	// 添加
	public int addProduct(Info_product info_product);

	// 查询列表显示
	public List<Info_product> findAllProduct();

	// 删除
	public int deleteByPrimaryKey(int id);
}

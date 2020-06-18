package cn.edu.ujn.cait037.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.ujn.cait037.dao.Info_product;
import cn.edu.ujn.cait037.dao.Info_productMapper;

@Service
@Transactional
public class Info_productServiceImpl implements IInfo_productService {
	@Autowired
	private Info_productMapper Info_productMapper;
	@Override
	public Info_product findProductById(Integer id) {
		// TODO Auto-generated method stub
		return this.Info_productMapper.selectByPrimaryKey(id);
	}

	@Override
	public int addProduct(Info_product info_product) {
		// TODO Auto-generated method stub
		int insert = this.Info_productMapper.insertSelective(info_product);
		return insert;
	}

	@Override
	public List<Info_product> findAllProduct() {
		// TODO Auto-generated method stub
		return this.Info_productMapper.findAllProduct();
	}

	@Override
	public int deleteByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}

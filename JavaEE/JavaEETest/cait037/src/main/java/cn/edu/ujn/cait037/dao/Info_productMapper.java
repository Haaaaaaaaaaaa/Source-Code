package cn.edu.ujn.cait037.dao;

import java.util.List;

import cn.edu.ujn.cait037.dao.Info_product;

public interface Info_productMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Info_product record);

	int insertSelective(Info_product record);

	Info_product selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Info_product record);

	int updateByPrimaryKey(Info_product record);

	List<Info_product> findAllProduct();
}
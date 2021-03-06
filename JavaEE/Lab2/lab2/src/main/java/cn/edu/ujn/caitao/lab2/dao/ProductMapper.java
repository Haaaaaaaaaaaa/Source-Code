package cn.edu.ujn.caitao.lab2.dao;

import java.util.List;

import cn.edu.ujn.caitao.lab2.dao.Product;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Product record);
    int insertSelective(Product record);
    Product selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(Product record);
    int updateByPrimaryKey(Product record);
    // 通过订单的id查询商品
    List<Product> findProductById(int id);				
}
package cn.edu.ujn.caitao.lab2.dao;

import java.util.List;
import java.util.Map;

import cn.edu.ujn.caitao.lab2.dao.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);				// 删除用户
    int insert(User record);						// 插入用户
    int insertSelective(User record);				// 使用动态sql插入用户   
    int updateByPrimaryKeySelective(User record);   // 使用动态sql更新用户信息
    int updateByPrimaryKey(User record);   			// 更新用户信息	
    User selectByPrimaryKey(Integer id);			// 查询单个用户信息   
    List<User> findAllUser();					    // 查询所有的用户
        
}
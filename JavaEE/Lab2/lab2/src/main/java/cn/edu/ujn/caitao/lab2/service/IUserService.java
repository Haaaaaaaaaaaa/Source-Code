package cn.edu.ujn.caitao.lab2.service;

import java.util.List;

import cn.edu.ujn.caitao.lab2.dao.User;

public interface IUserService {
	int deleteByPrimaryKey(Integer id);					// 删除用户
	int insert(User record);							// 插入用户
	int updateByPrimaryKey(User record);				// 更新用户
	User selectByPrimaryKey(Integer id);			    // 查询用户
	List<User> findAllUser();							// 查找所有的用户
}

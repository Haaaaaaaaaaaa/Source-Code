package cn.edu.ujn.reviewlab1.dao;

import java.util.List;

import cn.edu.ujn.reviewlab1.model.User;

public interface IUserDao {
//	添加用户
	public User addUser(User user);

//  查询所有用户
	public List<User> findAllUser();
}

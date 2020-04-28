package cn.edu.ujn.lab1.dao;

import java.util.List;

import cn.edu.ujn.lab1.model.User;

public interface IUserDao {
	// 返回一个User对象
	public User addUser(User user);

	// 列出所有用户
	public List<User> findAllUser();

	// 修改用户信息
	public int updateUser(User user);

	// 删除用户信息
	public int deleteUser(int uid);
}

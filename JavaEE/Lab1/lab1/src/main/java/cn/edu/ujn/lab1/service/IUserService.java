package cn.edu.ujn.lab1.service;

import java.util.List;

import cn.edu.ujn.lab1.model.Student;
import cn.edu.ujn.lab1.model.User;

public interface IUserService {
	// 添加
	public User register(User user, Student stu);

	// 列出所有用户
	public List<User> findAllUser();

	// 修改用户信息
	public int updateUser(User user);

	// 删除用户信息
	public int deleteUser(int uid);
}

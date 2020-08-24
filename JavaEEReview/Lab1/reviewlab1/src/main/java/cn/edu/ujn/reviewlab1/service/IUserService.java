package cn.edu.ujn.reviewlab1.service;

import java.util.List;

import cn.edu.ujn.reviewlab1.model.Student;
import cn.edu.ujn.reviewlab1.model.User;

public interface IUserService {

	public User addUser(User user, Student stu);

	public List<User> findAllUser();
}

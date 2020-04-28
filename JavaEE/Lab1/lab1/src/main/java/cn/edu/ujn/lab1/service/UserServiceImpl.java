package cn.edu.ujn.lab1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.ujn.lab1.dao.IStudentDao;
import cn.edu.ujn.lab1.dao.IUserDao;
import cn.edu.ujn.lab1.model.Student;
import cn.edu.ujn.lab1.model.User;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IStudentDao studentDao;

//	注册
	public User register(User user, Student stu) {
		// TODO Auto-generated method stub
		User addUser = this.userDao.addUser(user);
		stu.setUid(addUser.getUid());
		int addStudent = this.studentDao.addStudent(stu);
		return addUser;
	}

//	查询所有用户信息
	public List<User> findAllUser() {
		return userDao.findAllUser();
	}

//	修改用户信息
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

//	删除用户信息
	public int deleteUser(int uid) {
		return userDao.deleteUser(uid);
	}
}

package cn.edu.ujn.reviewlab1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.ujn.reviewlab1.dao.IStudentDao;
import cn.edu.ujn.reviewlab1.dao.IUserDao;
import cn.edu.ujn.reviewlab1.model.Student;
import cn.edu.ujn.reviewlab1.model.User;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	@Autowired
	private IStudentDao studentDao;

	@Override
	public User addUser(User user, Student stu) {
		// TODO Auto-generated method stub
		User addUser = userDao.addUser(user);
		stu.setUid(addUser.getUid());
		studentDao.addStudent(stu);
		return addUser;
	}

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return userDao.findAllUser();
	}

}

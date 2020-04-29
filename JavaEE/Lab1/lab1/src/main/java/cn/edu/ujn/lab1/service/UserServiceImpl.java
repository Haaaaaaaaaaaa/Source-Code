package cn.edu.ujn.lab1.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.ujn.lab1.dao.IStudentDao;
import cn.edu.ujn.lab1.dao.IUserDao;
import cn.edu.ujn.lab1.model.Student;
import cn.edu.ujn.lab1.model.User;
import cn.edu.ujn.lab1.util.MD5Encoder;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IStudentDao studentDao;

//	注册
	public User register(User user, Student stu) {
		// TODO Auto-generated method stub
//		密码加密
		try {
			String encoder = MD5Encoder.geMd5(user.getPassword());
			user.setPassword(encoder);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		try {
			String encoder = MD5Encoder.geMd5(user.getPassword());
			user.setPassword(encoder);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userDao.updateUser(user);
	}

//	删除用户信息
	public int deleteUser(int uid) {
		return userDao.deleteUser(uid);
	}
}

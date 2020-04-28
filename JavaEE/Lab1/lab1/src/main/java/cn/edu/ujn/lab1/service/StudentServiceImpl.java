package cn.edu.ujn.lab1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.ujn.lab1.dao.IStudentDao;
import cn.edu.ujn.lab1.dao.IUserDao;
import cn.edu.ujn.lab1.model.Student;
import cn.edu.ujn.lab1.model.User;

@Service
public class StudentServiceImpl implements IStudentService {
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IStudentDao studentDao;

//	添加
	public int addStudent(Student stu) {
		return studentDao.addStudent(stu);
	}

//	查询所有学生信息
	public List<Student> findAllStudent() {
		return studentDao.findAllStudent();
	}

//	修改学生信息
	public int updateStudent(Student student) {
		return studentDao.updateStudent(student);
	}

//	删除学生信息
	public int deleteStudent(int uid) {
		return studentDao.deleteStudent(uid);
	}
}

package cn.edu.ujn.lab1.service;

import java.util.List;

import cn.edu.ujn.lab1.model.Student;
import cn.edu.ujn.lab1.model.User;

public interface IStudentService {
	// 添加
	public int addStudent(Student stu);

	// 列出所有学生
	public List<Student> findAllStudent();

	// 修改学生信息
	public int updateStudent(Student stu);

	// 删除学生信息
	public int deleteStudent(int uid);
}

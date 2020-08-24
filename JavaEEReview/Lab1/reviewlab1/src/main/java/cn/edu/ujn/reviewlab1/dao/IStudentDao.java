package cn.edu.ujn.reviewlab1.dao;

import java.util.List;

import cn.edu.ujn.reviewlab1.model.Student;

public interface IStudentDao {
	// 添加学生
	public int addStudent(Student student);

	// 查询所有学生
	public List<Student> findAllStudent();
}

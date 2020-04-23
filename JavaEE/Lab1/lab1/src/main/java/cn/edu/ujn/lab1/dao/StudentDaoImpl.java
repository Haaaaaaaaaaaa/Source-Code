package cn.edu.ujn.lab1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.edu.ujn.lab1.model.Student;

@Repository
public class StudentDaoImpl implements IStudentDao {
	// 依赖注入
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int addStudent(Student stu) {
		// TODO Auto-generated method stub
		String sql = "insert into student(uid,stunumber,stuname) value(?,?,?)";
		Object[] obj = new Object[] { stu.getUid(), stu.getStunumber(), stu.getStuname() };
		int update = this.jdbcTemplate.update(sql, obj);
		return update;
	}
}

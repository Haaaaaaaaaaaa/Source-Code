package cn.edu.ujn.reviewlab1.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.edu.ujn.reviewlab1.model.Student;

@Repository("studentDao")
public class StudentDaoImpl implements IStudentDao {
	// 依赖注入
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int addStudent(Student student) {
		// TODO Auto-generated method stub
		String sql = "insert into student(uid,stunumber,stuname) value(?,?,?)";
		Object[] obj = new Object[] { student.getUid(), student.getStunumber(), student.getStuname() };
		int num = this.jdbcTemplate.update(sql, obj);
		return num;
	}

	@Override
	public List<Student> findAllStudent() {
		// TODO Auto-generated method stub
		String sql = "select * from student";
		BeanPropertyRowMapper<Student> rowMapper = new BeanPropertyRowMapper<Student>(Student.class);
		return this.jdbcTemplate.query(sql, rowMapper);
	}

}

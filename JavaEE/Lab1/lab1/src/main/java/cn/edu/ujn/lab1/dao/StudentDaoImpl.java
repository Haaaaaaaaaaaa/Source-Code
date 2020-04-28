package cn.edu.ujn.lab1.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.edu.ujn.lab1.model.Student;

@Repository
public class StudentDaoImpl implements IStudentDao {
	// 依赖注入
	@Autowired
	private JdbcTemplate jdbcTemplate;

//	添加学生
	public int addStudent(Student stu) {
		// TODO Auto-generated method stub
		String sql = "insert into student(uid,stunumber,stuname) value(?,?,?)";
		Object[] obj = new Object[] { stu.getUid(), stu.getStunumber(), stu.getStuname() };
		int update = this.jdbcTemplate.update(sql, obj);
		return update;
	}

//	列出所有学生信息
	public List<Student> findAllStudent() {
		// 定义SQL语句
		String sql = "select * from student";
		// 创建新的BeanPropertyRowMapper对象
		BeanPropertyRowMapper<Student> rowMapper = new BeanPropertyRowMapper<Student>(Student.class);
		// 执行SQL，返回结果
		return this.jdbcTemplate.query(sql, rowMapper);
	}

//	修改学生信息
	public int updateStudent(Student stu) {
		// 定义SQL语句
		String sql = "update student set stunumber=?,stuname=? where uid=?";
		Object[] obj = new Object[] { stu.getStunumber(), stu.getStuname(), stu.getUid() };
		return this.jdbcTemplate.update(sql, obj);
	}

//	删除学生信息
	public int deleteStudent(int uid) {
		// 定义SQL语句
		String sql = "delete from student where uid=?";
		return this.jdbcTemplate.update(sql, uid);
	}
}

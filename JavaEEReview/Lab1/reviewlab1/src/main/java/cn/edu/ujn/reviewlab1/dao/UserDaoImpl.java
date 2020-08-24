package cn.edu.ujn.reviewlab1.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.edu.ujn.reviewlab1.model.User;

@Repository("userDao")
public class UserDaoImpl implements IUserDao {
	// 注入JDBC
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		String sql = "insert into user(username,password) value(?,?)";
		Object[] obj = new Object[] { user.getUsername(), user.getPassword() };
		int i = jdbcTemplate.update(sql, obj);
		if (i > 0) {
			String sql2 = "select max(uid) as uid,username,password from user";
			BeanPropertyRowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
			User user1 = jdbcTemplate.queryForObject(sql2, rowMapper);
			return user1;
		}
		return null;
	}

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		String sql = "select * from user";
		BeanPropertyRowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		return jdbcTemplate.query(sql, rowMapper);
	}

}

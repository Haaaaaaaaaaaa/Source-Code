package cn.edu.ujn.lab1.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.edu.ujn.lab1.model.User;

@Repository
public class UserDaoImpl implements IUserDao {
	// 依赖注入
	@Autowired
	private JdbcTemplate jdbcTemplate;

//	添加账户
	public User addUser(User user) {
		// TODO Auto-generated method stub
		String sql = "insert into user(username,password) value(?,?)";
		// 创建对象数组
		Object[] obj = new Object[] { user.getUsername(), user.getPassword() };
		int update = this.jdbcTemplate.update(sql, obj);
		if (update > 0) {
			// 值得思考，低版本SQL可以使用
//			String sql2 = "select max(uid) as uid,username,password from user";
			String sql2 = "select * from user where username=? and password=?";
			BeanPropertyRowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
			// queryForObject方法只能返回"1",当数据库中要多条username相同的记录时，就会报错。
			// 解决方法：
			// 1、将数据库中的记录删除，相同的只有一条就可以了（有局限性）
			// 2、通过更换方法：使用query方法返回list对象（该方法能返回所有查询记录）
			User queryForObject = this.jdbcTemplate.queryForObject(sql2, rowMapper, user.getUsername(),
					user.getPassword());
			return queryForObject;
			// 方法2示例：(有待改进)
//			List<User> list=jdbcTemplate.query(sql2, rowMapper,user.getUsername(), user.getPassword());
//			return list;
		}
		return null;
	}

//	查询所有账户信息
	public List<User> findAllUser() {
		// 定义SQl语句
		String sql = "select * from user";
		// 创建新的BeanPropertyRowMapper对象
		BeanPropertyRowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		// 执行SQl语句，返回结果
		return this.jdbcTemplate.query(sql, rowMapper);
	}

//	修改用户信息
	public int updateUser(User user) {
		// 定义SQL语句
		String sql = "update user set username=?,password=? where uid=?";
//		Object obj = new Object[] { user.getUsername(), user.getPassword(), user.getUid() };
		return this.jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getUid());
		// 这样写为什么不行呢？
//		return this.jdbcTemplate.update(sql,obj);
	}

//	删除用户信息
	public int deleteUser(int uid) {
		// 定义SQL语句
		String sql = "delete from user where uid=?";
		return this.jdbcTemplate.update(sql, uid);
	}
}

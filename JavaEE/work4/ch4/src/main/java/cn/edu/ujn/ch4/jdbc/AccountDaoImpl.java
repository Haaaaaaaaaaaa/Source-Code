package cn.edu.ujn.ch4.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {
//@Autowired根据类型自动注入
	@Autowired
//声明JdbcTemplate属性极其setter方法
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
//添加账户
	public int addAccount(Account account) {
		// TODO Auto-generated method stub
		String sql = "insert into account(username,balance) value(?,?)";
		//定义数组来存放SQL语句中的参数
		Object[] obj = new Object[] {account.getUsername(), account.getBalance()};
		//执行添加操作。返回的是受SQL语句影响的记录条数
		int update = this.jdbcTemplate.update(sql, obj);
		return update;
	}
//更新账户
	public int updateAccount(Account account) {
		//定义SQL语句
		String sql="update account set username=?,balance=? where id = ?";
		//定义数组来存放SQL语句中的参数
		Object[] params =new Object[] {account.getUsername(),account.getBalance(),account.getId()};
		//执行SQL语句，返回受影响的记录条数
		int num = this.jdbcTemplate.update(sql,params);
		return num;
	}
//删除账户
	public int deleteAccount(int id) {
		//定义SQl语句
		String sql="delete from account where id=?";
		//执行操作，返回受影响的记录条数
		int num = this.jdbcTemplate.update(sql,id);
		return num;
	}
//通过ID查询账户信息
	public Account findAccountById(int id) {
	    //定义SQL语句
	    String sql = "select * from account where id = ?";
	    // 创建一个新的BeanPropertyRowMapper对象
	    RowMapper<Account> rowMapper = new BeanPropertyRowMapper<Account>(Account.class);
	    // 将id绑定到SQL语句中，并通过RowMapper返回一个Object类型的单行记录
	    return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
	}
//查询所有账户信息
	public List<Account> findAllAccount() {
		// TODO Auto-generated method stub
		//定义SQL语句
		String sql="select * from account";
		//创建一个性的BeanPropertyRowMapper对象
		RowMapper<Account>rowMapper=new BeanPropertyRowMapper<Account>(Account.class);
		//执行查询，通过RowMapper返回结果
		return this.jdbcTemplate.query(sql,rowMapper);
	}
}

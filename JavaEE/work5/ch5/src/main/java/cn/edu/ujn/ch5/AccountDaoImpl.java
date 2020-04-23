package cn.edu.ujn.ch5;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	public int addAccount(Account account) {
		// TODO Auto-generated method stub
		String sql = "insert into account(username,balance) value(?,?)";
		Object[] obj = new Object[] {account.getUsername(), account.getBalance()};
		int num = this.jdbcTemplate.update(sql, obj);
		return num;

	}

	public List<Account> findAllAccount() {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
	public void transfer(String outUser, String inUser, double money) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update("update account set balance = balance-? where username=?",money,outUser);
		//创造异常
		int i=1/0;
		this.jdbcTemplate.update("update account set balance = balance+? where username=?",money,inUser);
	}

}

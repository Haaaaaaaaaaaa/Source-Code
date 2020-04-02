package cn.edu.ujn.ch4.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {
//	@Autowired根据类型自动注入
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int addAccount(Account account) {
		// TODO Auto-generated method stub
		String sql = "insert into account(username,balance) value(?,?)";
		Object[] obj = new Object[] { account.getUsername(), account.getBalance() };
		int update = this.jdbcTemplate.update(sql, obj);
		return update;
	}

	public List<Account> findAllAccount() {
		// TODO Auto-generated method stub
		return null;
	}

}

package cn.edu.ujn.ch4.jdbc;

import java.util.List;

public interface IAccountDao {
	public int addAccount(Account account);
	public List<Account> findAllAccount();
}

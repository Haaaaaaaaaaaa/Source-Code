package cn.edu.ujn.ch4.jdbc;

import java.util.List;

public interface IAccountDao {
//	添加账户
	public int addAccount(Account account);
//	查询所有账户
	public List<Account> findAllAccount();
//	更新
	public int updateAccount(Account account);
//	删除
	public int deleteAccount(int id);
//	通过ID查询
	public Account findAccountById(int id);
	
	
}

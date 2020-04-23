package cn.edu.ujn.ch5;

import java.util.List;

public interface IAccountDao {

	public int addAccount(Account account);
	public List<Account> findAllAccount();
	public void transfer(String outUser,String inUser,double money);
}

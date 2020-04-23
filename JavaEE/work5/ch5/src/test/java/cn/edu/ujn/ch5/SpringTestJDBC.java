package cn.edu.ujn.ch5;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class SpringTestJDBC {

	@Autowired
	private IAccountDao accountDao;

	@Autowired
	private Account account;
////测试添加用户
//	@Test
//	public void addTest() {
//		account.setUsername("张标");
//		account.setBalance(1000.00);
//		
//		int i = accountDao.addAccount(account);
//		System.out.println("插入成功："+i);			
//	}
//测试转账
	@Test
	public void transferTest() {
		accountDao.transfer("ct", "zym", 100.00);
		System.out.println("转账成功!");
	}
}

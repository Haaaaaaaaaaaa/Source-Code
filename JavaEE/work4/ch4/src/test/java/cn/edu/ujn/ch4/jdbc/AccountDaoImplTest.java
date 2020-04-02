package cn.edu.ujn.ch4.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//以什么样的方式运行
@RunWith(SpringJUnit4ClassRunner.class)
//指定spring的配置文件
@ContextConfiguration(locations ="classpath:applicationContext.xml")
public class AccountDaoImplTest {
	@Autowired
//	注入一个实例
	private IAccountDao adao;
//	需要test注解
	@Test
	public void addAccountTest() {
		Account aa = new Account();
		aa.setUsername("caitao");
		aa.setBalance(666.6);
		
		int num =adao.addAccount(aa);
		
		System.out.println("插入成功"+num);
	}
}

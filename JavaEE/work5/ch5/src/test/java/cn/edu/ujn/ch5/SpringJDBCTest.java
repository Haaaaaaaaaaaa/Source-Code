package cn.edu.ujn.ch5;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringJDBCTest {
	@Test
	public void addTest() {
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		IAccountDao accDao = 
				(IAccountDao) context.getBean("accountDao");
		
		Account aa = new Account();
		aa.setUsername("zhangsan");
		aa.setBalance(100000.00);
		
		int i = accDao.addAccount(aa);
		System.out.println("插入成功："+i);
		
	}

}

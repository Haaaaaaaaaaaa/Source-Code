package cn.edu.ujn.ch4.jdbc;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//以什么样的方式运行
@RunWith(SpringJUnit4ClassRunner.class)
//指定spring的配置文件
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AccountDaoImplTest {
	@Autowired
//注入一个实例
//对于注入实例，还有一种犯法就是在每个**Test()方法中都使用加载配置文件的方式，会比较繁琐
//附上代码：
//	  // 加载配置文件
//    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//    // 获取AccountDao实例
//    AccountDao accountDao =(AccountDao) applicationContext.getBean("accountDao");

	private IAccountDao accountTest;

//注意：一定要在测试方法前加上@Test，不然会报错（no runable mathod）,而且Test的T要大写

////测试创建表
//	@Test
//	public void mainTest() {
//		// 加载配置文件
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//		// 获取JdbcTemplate实例
//		JdbcTemplate jdTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
//		// 使用execute()方法执行SQL语句，创建用户账户管理表account
//		jdTemplate.execute("create table account(" + "id int primary key auto_increment," + "username varchar(50),"
//				+ "balance double)");
//		System.out.println("账户表account创建成功！");
//	}

////测试添加账户
//	@Test
//	public void addAccountTest() {
//		Account account = new Account();
//		account.setUsername("caitao1");
//		account.setBalance(666.6);
//		int num = accountTest.addAccount(account);
//		System.out.println("插入成功" + num);
//	}

////测试更新账户信息
//	@Test
//	public void updateAccountTest() {
//		// 创建Account对象，并向Account对象中添加数据
//		Account account = new Account();
//		account.setUsername("ct");
//		account.setBalance(555.5);
//		account.setId(1);
//		// 执行updateAccount（）方法，并获取返回结果
//		int num = accountTest.updateAccount(account);
//		if (num > 0) {
//			System.out.println("成功修改了" + num + "条数据！");
//		} else {
//			System.out.println("修改操作执行失败！");
//		}
//	}

////测试通过ID删除账户
//	@Test
//	public void deleteAccountTest() {
//		// 执行deleteAccount()方法，并获取返回结果
//		int num = accountTest.deleteAccount(5);
//		if (num > 0) {
//			System.out.println("成功删除了" + num + "条数据！");
//		} else {
//			System.out.println("删除操作执行失败！");
//		}
//	}

////测试通过ID查询账户信息
//	@Test
//	public void findAccountByIdTest() {
//		// 创建Account对象
//		Account account = new Account();
//		// 查询id=1的用户信息
//		account = accountTest.findAccountById(1);
//		System.out.println(account);
//	}

//测试查询所有账户信息
	@Test
	public void findAllAccountTest() {
		// 执行findAllAccount()方法，并获取返回后结果
		List<Account> account = accountTest.findAllAccount();
		// 循环输出集合中的对象
		for (Account act : account) {
			System.out.println(act);
		}
	}
}

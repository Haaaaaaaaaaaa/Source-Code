package cn.edu.ujn.ch5;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCTest {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbcTem = (JdbcTemplate) context.getBean("jdbcTemplate");
		jdbcTem.execute("create table account(id int primary key auto_increment,username varchar(50),balance double)");
		System.out.println("账户表创建成功！");
	}

}

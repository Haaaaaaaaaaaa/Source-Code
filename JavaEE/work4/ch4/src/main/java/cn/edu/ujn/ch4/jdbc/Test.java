package cn.edu.ujn.ch4.jdbc;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class Test {
	public static void main(String[] args) {
		String sql="create table account(id int primary key auto_increment,"
				+ "username varchar(50),"
				+ "balance double)";
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		jdbcTemplate.execute(sql);
		System.out.println("账户表创建成功");
	}
}

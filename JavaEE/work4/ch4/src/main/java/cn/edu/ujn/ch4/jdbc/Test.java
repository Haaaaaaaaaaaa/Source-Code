package cn.edu.ujn.ch4.jdbc;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class Test {
	public static void main(String[] args) {
		String sql="create table account(id int primary key auto_increment,"
				+ "username varchar(50),"
				+ "balance double)";
		//加载配置文件
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//获取jdbcTemplate实例
		JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		//执行SQL语句
		jdbcTemplate.execute(sql);
		//提示创建成功
		System.out.println("账户表创建成功");
	}
}

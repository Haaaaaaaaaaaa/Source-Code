package cn.edu.ujn.ch7.utils;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtils {
//	成员变量
	private static SqlSessionFactory sqlSessionFactory;

//	静态代码段
	static {
		try {
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			// 赋值給成员变量
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static SqlSession getSession() {
		return sqlSessionFactory.openSession();
	}
}

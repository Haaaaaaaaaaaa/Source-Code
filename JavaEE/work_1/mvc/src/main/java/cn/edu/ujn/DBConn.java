package cn.edu.ujn;

import java.sql.*;

public class DBConn {
	public Connection getConnection() {
		try {
//			加载注册驱动程序
			try {
				Class.forName("org.gjt.mm.mysql.Driver");
			} catch (Exception e) {
				e.printStackTrace();
			}	
			String url="jdbc:mysql://127.0.0.1:3306/javaee?user=root&password=&characterEncoding=gb2312";
			return DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return null;
	}
}

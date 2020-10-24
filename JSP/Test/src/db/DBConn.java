package db;

import java.sql.*;

public class DBConn {

	public Connection getConnection() {
		try {
//			¼ÓÔØ×¢²áÇý¶¯³ÌÐò
			try {
				Class.forName("org.gjt.mm.mysql.Driver");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String url = "jdbc:mysql://127.0.0.1:3306/stu?user=root&password=123456&characterEncoding=gb2312";
			return DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

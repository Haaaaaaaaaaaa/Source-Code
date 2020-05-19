package cn.edu.ujn.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	public Connection getConnection() {
		try {
			try {
				Class.forName("org.gjt.mm.mysql.Driver");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String url = "jdbc:mysql://127.0.0.1:3306/javarmi?user=root&password=&characterEncoding=gb2312";
			return DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

package db;

import java.sql.*;

public class DBConn {
	Connection conn;
	Statement stat;
	ResultSet rs;
	public Connection getConnection() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			String url="jdbc:mysql://127.0.0.1:3306/sun?user=root&password=&characterEncoding=gb2312";
			conn= DriverManager.getConnection(url);
			stat = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	public ResultSet login(String username,String pwd) {	
		try {
			String sql="select * from login where User='"+username+"' and pwd='" + pwd+"'";
			rs=stat.executeQuery(sql);
//			�鿴�Ƿ�����ȷ��SQL���
			System.out.println(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sport;

import java.sql.*;

/**
 *
 * @author 哈哈
 */
public class Sport {
    public static void main(String[] args) throws SQLException {
      Connection conn=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;databaseName=Sport";
			 conn = DriverManager.getConnection(url,"haha","123123");
			System.out.println("数据库连接成功");
		
		}
		catch(Exception e) {
			System.out.println("数据库连接失败\n" + e.toString());
		}
                Statement stmt = conn.createStatement();
        //ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句   ，返回一个结果集（ResultSet）对象。
        ResultSet rs = stmt.executeQuery("select Sno,Sname from Athlete where Sno=20170609002");
        while(rs.next()){//如果对象中有数据，就会循环打印出来
           String sno = rs.getString("Sname");
            System.out.println(sno);
        }
    }
    
}

package service;

import java.sql.*;

import db.DBConn;
import model.User;

public class UserService {
	public boolean checkLogin(User bean) {
//		从JavaBean中获得属性值
		String username=bean.getUser();
		String pwd=bean.getPwd();
//		创建连接
		try {
			DBConn db=new DBConn();
//			传统连接方式
			Connection conn = db.getConnection();
//			不使用预处理语句
//			Statement stat = conn.createStatement();
//			String sql="select * from login where User='"+username+"' and pwd='" + pwd+"'";
//			ResultSet rs=stat.executeQuery(sql);
//			使用预处理语句
			PreparedStatement pre=conn.prepareStatement("select * from login where User='"+username+"' and pwd='" + pwd+"'");
			ResultSet rs=pre.executeQuery();
			if(rs.next())
			    return true;
			else
			    return false;
			} catch (SQLException e) {
				System.out.println("数据库连接出错！");
			return false;
		}	
	}
}


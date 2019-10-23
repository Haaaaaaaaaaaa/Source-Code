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
			Connection conn = db.getConnection();
			ResultSet rs = db.login(username,pwd);
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


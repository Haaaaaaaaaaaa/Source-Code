package service;

import java.sql.*;

import db.DBConn;
import model.User;

public class UserService {
	public boolean checkLogin(User bean) {
//		��JavaBean�л������ֵ
		String username=bean.getUser();
		String pwd=bean.getPwd();
//		��������
		try {
			DBConn db=new DBConn();
			Connection conn = db.getConnection();
			ResultSet rs = db.login(username,pwd);
			if(rs.next())
			    return true;
			else
			    return false;
			} catch (SQLException e) {
			System.out.println("���ݿ����ӳ���");
			return false;
		}	
	}
}


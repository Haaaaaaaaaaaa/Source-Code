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
//			��ͳ���ӷ�ʽ
			Connection conn = db.getConnection();
//			��ʹ��Ԥ�������
//			Statement stat = conn.createStatement();
//			String sql="select * from login where User='"+username+"' and pwd='" + pwd+"'";
//			ResultSet rs=stat.executeQuery(sql);
//			ʹ��Ԥ�������
			PreparedStatement pre=conn.prepareStatement("select * from login where User='"+username+"' and pwd='" + pwd+"'");
			ResultSet rs=pre.executeQuery();
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


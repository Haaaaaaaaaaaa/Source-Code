package service;

import java.sql.*;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;

import db.DBConn;
import model.User;

public class Addservice {

	public boolean add(User bean) {
		String username=bean.getUsername();
		String password=bean.getPassword();
//		���ֵ
		System.out.println(username);
		System.out.println(password);
		System.out.println("addservice��ȡ�ɹ�");
		
		try {
			DBConn db=new DBConn();
//			��ͳ���ӷ�ʽ����ʹ��Ԥ�������
//			Connection conn = db.getConnection();
//			Statement stat = conn.createStatement();
//			String sql1="select * from user where username='"+username+"' and password='" + password+"'";
//			String sql="insert into user(username,password)VALUES('"+username+"','"+password+"')";
//			ResultSet rs=stat.executeQuery(sql1);			
//			ʹ��Ԥ�������
			Connection conn = db.getConnection();
			PreparedStatement pre=conn.prepareStatement("select * from user where username=? and password=?");
			pre.setString(1,username);
			pre.setString(2,password);
			ResultSet rs=pre.executeQuery();
			if(!rs.next()){
				PreparedStatement pre1=conn.prepareStatement("insert into user(username,password) VALUES(?,?)");
				pre1.setString(1,username);
				pre1.setString(2,password);
			    pre1.executeUpdate();
//			    ��ʹ��Ԥ�������
//				stat.executeUpdate(sql);
			    conn.close();
			    pre.close();
			    pre1.close();
			    return true;
			    }
			else
			    return false;
			} catch (SQLException e) {
				System.out.println("���ݿ����ӳ���");
				return false;
			}
	}
}

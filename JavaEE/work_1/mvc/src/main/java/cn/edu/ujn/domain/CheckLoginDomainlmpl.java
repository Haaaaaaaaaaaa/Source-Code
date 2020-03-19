package cn.edu.ujn.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.edu.ujn.db.DBConn;
import cn.edu.ujn.javabean.User;


public class CheckLoginDomainlmpl implements ICheckLoginDomain{
	public boolean checkLogin(User u) {
		String username=u.getUsername();
		String password=u.getPassword();
		System.out.print(username+"和"+password);
		try {
			DBConn db=new DBConn();
			Connection conn = db.getConnection();
			PreparedStatement pre=conn.prepareStatement("select * from user where username='" + username + "' and password='" + password + "'");
			ResultSet rs=pre.executeQuery();
			if(rs.next())
				return true;
			else 
				return false;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}

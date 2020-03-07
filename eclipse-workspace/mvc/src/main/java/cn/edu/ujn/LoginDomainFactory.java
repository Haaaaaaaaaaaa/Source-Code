package cn.edu.ujn;

import java.sql.*;

public class LoginDomainFactory {
	public static ICheckLoginDomain getInstance() {
		ICheckLoginDomain loginDomain = null;
		try {
			loginDomain=new CheckLoginDomainlmpl();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return loginDomain;
	}
}

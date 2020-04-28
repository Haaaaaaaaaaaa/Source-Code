package cn.edu.ujn.factory;

import java.sql.*;

import cn.edu.ujn.domain.CheckLoginDomainlmpl;
import cn.edu.ujn.domain.ICheckLoginDomain;

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

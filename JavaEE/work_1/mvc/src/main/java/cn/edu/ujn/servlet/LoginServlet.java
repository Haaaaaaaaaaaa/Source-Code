package cn.edu.ujn.servlet;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.ujn.domain.ICheckLoginDomain;
import cn.edu.ujn.factory.LoginDomainFactory;
import cn.edu.ujn.javabean.User;

@WebServlet("/login")
//这相当于配置web.xml文件（以前JSP的方法）
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doPost(req, resp);

		String username = req.getParameter("username");
		String pwd = req.getParameter("pwd");
		User u = new User();
		u.setUsername(username);
		u.setPassword(pwd);
		ICheckLoginDomain dlDomain = LoginDomainFactory.getInstance();
		if (dlDomain.checkLogin(u))
			resp.sendRedirect("loginok.jsp");
		else
			resp.sendRedirect("loginfalse.jsp");
	}
}

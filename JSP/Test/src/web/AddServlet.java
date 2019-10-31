package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DBConn;
import model.User;
import service.UserService;

public class AddServlet extends HttpServlet {
@Override
public void init() throws ServletException {
	super.init();
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("gb2312");
	String username = req.getParameter("username");
	String password = req.getParameter("password");
//	检测值
	System.out.println(username);
	System.out.println(password);
	System.out.println("addservelt获取成功");
	if(username.length()>0&&password.length()>0){
//	写入JavaBean
	User bean=new User();
	HttpSession session=req.getSession(true);
	session.setAttribute("bean",bean);
	bean.setUsername(username);
	bean.setPassword(password);
	UserService addservice = new UserService();
//	判断是否注册成功
	if(addservice.add(bean)){	
//		转发
		RequestDispatcher dis=req.getRequestDispatcher("list.jsp"); 
		dis.forward(req, resp); 
	}else{
//		重定向
		resp.sendRedirect("register.jsp");	
	}
	}else
		resp.sendRedirect("register.jsp?Null=yes");	
	}
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}

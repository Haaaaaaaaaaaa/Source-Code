package web;
import java.sql.*;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import model.User;
import service.UserService;

public class LoginServlet extends HttpServlet {
@Override
	public void init() throws ServletException {
	super.init();
}

@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String username = req.getParameter("username");
	String pwd = req.getParameter("pwd");
//	创建JavaBean
	User bean=new User();
	bean.setUser(username);
	bean.setPwd(pwd);
	UserService service = new UserService();
//	判断是否登录成功
	if(service.checkLogin(bean)){
//		重定向
//		resp.sendRedirect("../ok.jsp");
//		转发
		RequestDispatcher dis=req.getRequestDispatcher("ok.jsp"); 
		dis.forward(req, resp); 
	}else{
//		重定向
		resp.sendRedirect("login.jsp");	
	}
}

@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}

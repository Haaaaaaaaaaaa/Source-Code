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
	req.setCharacterEncoding("UTF-8");
	String username = req.getParameter("username");
	String password = req.getParameter("password");
//	检测值
	System.out.println(username);
	System.out.println(password);
	System.out.println("addservelt获取成功");
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
	}
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
//protected void Store(HttpServletRequest request, HttpServletResponse response) throws SQLException {
//    DBConn dbConn = new DBConn();
//    Connection conn = dbConn.getConnection();
//    PreparedStatement pre = conn.prepareStatement("select * from user order by id");
//    ResultSet rs = pre.executeQuery ();
//    int n;
//    rs.last();
//    n = rs.getRow();
//    rs.beforeFirst();
//    User []users = new User[n];
//    for (int i = 0; i < n; i++) {
//        users[i] = new User();
//    }
//    int i = 0;
//    System.out.println(n);
//    while(rs.next()){
//        users[i].setId(rs.getInt("id"));
//        users[i].setUsername(rs.getString("username"));
//        users[i].setPassword(rs.getString("password"));
//        request.getSession().setAttribute("users"+i,users[i]);
//        i++;
//    }
//}
}

package web;
import java.sql.*;
import java.io.IOException;

//import javax.servlet.ServletException;
import javax.servlet.*;
import javax.servlet.http.*;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import model.User;

public class LoginServlet extends HttpServlet {
@Override
	public void init() throws ServletException {
	// TODO Auto-generated method stub
	super.init();
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	String username = req.getParameter("username");
	String pwd = req.getParameter("pwd");
	User dl=new User();
	dl.setUser(username);
	dl.setPwd(pwd);
	
	
	try {
		Class.forName("org.gjt.mm.mysql.Driver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	try {
		String url="jdbc:mysql://127.0.0.1:3306/sun";
		Connection con;
		Statement s;
		ResultSet rs;
		
		con = DriverManager.getConnection(url,username,pwd);
	    s = con.createStatement();
		String sql="select * from login where User='"+username+"' and pwd='" + pwd+"'";
	    rs = s.executeQuery(sql);
	    
		int tag=0;
		
		if(rs.next())        
		{
		  RequestDispatcher dis=req.getRequestDispatcher("ok.jsp"); 
		  dis.forward(req, resp); 
		  tag=1;
		 }
		if(tag==0) {
		  resp.sendRedirect("login.jsp");
		}       
		rs.close();
		s.close(); 
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
}

package ch5;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class resultServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        HttpSession session = request.getSession(true);
        int i = (Integer)(session.getAttribute("count"));
        int value = (Integer)(session.getAttribute("save"));
        String num = request.getParameter("guess");
        int gue = Integer.parseInt(num);
        session.setAttribute("guess", gue);
        session.setAttribute("count",i+1);
        if (gue > value){
            response.sendRedirect("large.jsp");
        }else if(gue < value){
            response.sendRedirect("small.jsp");
        }else{
//            response.sendRedirect("success.jsp");
        	out.print("恭喜你，猜对了"+"<br>");
        	Integer integer=(Integer)session.getAttribute("count");
        	out.print("你共猜了"+integer+"次"+"<br>");
        	out.print("这个数字就是"+gue);
        }
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
}

package service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Area;

public class AreaServlet extends HttpServlet {
@Override
public void init() throws ServletException {
	// TODO Auto-generated method stub
	super.init();
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Area dataBean=new Area(); 
		HttpSession session =req.getSession(true);
		session.setAttribute("data",dataBean);
		try{
			double a=Double.parseDouble(req.getParameter("a"));
			double b=Double.parseDouble(req.getParameter("b"));
			double c=Double.parseDouble(req.getParameter("c"));
			dataBean.setA(a);
			dataBean.setB(b);
			dataBean.setC(c);
			if(a<b+c&&b<a+c&&c<a+b){
				double p=-1;
				double s=-1;
				p=(a+b+c)/2.0;
				s=Math.sqrt(p*(p-a)*(p-b)*(p-c));
				dataBean.setArea1(s);
			}
			else{
				dataBean.setArea1(-1);
				dataBean.setMess("不能构成三角形");
			}	
		}catch(Exception e){
			dataBean.setArea1(-1);
		}
		try{
			double d=Double.parseDouble(req.getParameter("sd"));
			double e=Double.parseDouble(req.getParameter("xd"));
			double f=Double.parseDouble(req.getParameter("g"));
			dataBean.setSd(d);
			dataBean.setXd(e);
			dataBean.setG(f);
			double s2=-1;
			s2=(d+e)*f/2.0;
			dataBean.setArea2(s2);
		}catch(Exception e){
			dataBean.setArea2(-1);
		}
		RequestDispatcher dispatcher= req.getRequestDispatcher("showResult.jsp");
		dispatcher.forward(req, resp);
	}
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doPost(req,resp);
	}
}

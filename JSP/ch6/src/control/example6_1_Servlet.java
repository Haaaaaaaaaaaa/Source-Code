package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class example6_1_Servlet extends HttpServlet {
@Override
public void init(ServletConfig config) throws ServletException {
	// TODO Auto-generated method stub
	super.init(config);
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//创建JavaBean对象
		example6_1_Bean seriesData=new example6_1_Bean();
		req.setAttribute("seriesData", seriesData);
		//将seriesData存到request中
		double a=Double.parseDouble(req.getParameter("firstItem"));
		double d=Double.parseDouble(req.getParameter("var"));
		int n=Integer.parseInt(req.getParameter("number"));
		//将数据存储在数据模型seriesData中
		seriesData.setFirstItem(a);
		seriesData.setVar(d);
		seriesData.setNumber(n);
		double sum=0,item=a;
		int i=1;
		//计算等差数列的和
		seriesData.setName("等差数列的公差");
		while(i<=n){
			sum=sum+item;
			i++;
			item=item+d;
		}
		seriesData.setSum(sum);
		//请求example6_1_show.jap显示seriesData中的数据
		RequestDispatcher dispatcher=req.getRequestDispatcher("example6_1_show.jsp");
		dispatcher.forward(req,resp);   	
	}
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//创建JavaBean对象
	example6_1_Bean seriesData=new example6_1_Bean();
	req.setAttribute("seriesData", seriesData);
	//将seriesData存到request中
	double a=Double.parseDouble(req.getParameter("firstItem"));
	double d=Double.parseDouble(req.getParameter("var"));
	int n=Integer.parseInt(req.getParameter("number"));
	//将数据存储在数据模型seriesData中
	seriesData.setFirstItem(a);
	seriesData.setVar(d);
	seriesData.setNumber(n);
	double sum=0,item=a;
	int i=1;
	//计算等差数列的和
	seriesData.setName("等比数列的公差");
	while(i<=n){
		sum=sum+item;
		i++;
		item=item*d;
	}
	seriesData.setSum(sum);
	//请求example6_1_show.jap显示seriesData中的数据
	RequestDispatcher dispatcher=req.getRequestDispatcher("example6_1_show.jsp");
	dispatcher.forward(req,resp);   
	}
}

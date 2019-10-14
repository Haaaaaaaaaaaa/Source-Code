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
		//����JavaBean����
		example6_1_Bean seriesData=new example6_1_Bean();
		req.setAttribute("seriesData", seriesData);
		//��seriesData�浽request��
		double a=Double.parseDouble(req.getParameter("firstItem"));
		double d=Double.parseDouble(req.getParameter("var"));
		int n=Integer.parseInt(req.getParameter("number"));
		//�����ݴ洢������ģ��seriesData��
		seriesData.setFirstItem(a);
		seriesData.setVar(d);
		seriesData.setNumber(n);
		double sum=0,item=a;
		int i=1;
		//����Ȳ����еĺ�
		seriesData.setName("�Ȳ����еĹ���");
		while(i<=n){
			sum=sum+item;
			i++;
			item=item+d;
		}
		seriesData.setSum(sum);
		//����example6_1_show.jap��ʾseriesData�е�����
		RequestDispatcher dispatcher=req.getRequestDispatcher("example6_1_show.jsp");
		dispatcher.forward(req,resp);   	
	}
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//����JavaBean����
	example6_1_Bean seriesData=new example6_1_Bean();
	req.setAttribute("seriesData", seriesData);
	//��seriesData�浽request��
	double a=Double.parseDouble(req.getParameter("firstItem"));
	double d=Double.parseDouble(req.getParameter("var"));
	int n=Integer.parseInt(req.getParameter("number"));
	//�����ݴ洢������ģ��seriesData��
	seriesData.setFirstItem(a);
	seriesData.setVar(d);
	seriesData.setNumber(n);
	double sum=0,item=a;
	int i=1;
	//����Ȳ����еĺ�
	seriesData.setName("�ȱ����еĹ���");
	while(i<=n){
		sum=sum+item;
		i++;
		item=item*d;
	}
	seriesData.setSum(sum);
	//����example6_1_show.jap��ʾseriesData�е�����
	RequestDispatcher dispatcher=req.getRequestDispatcher("example6_1_show.jsp");
	dispatcher.forward(req,resp);   
	}
}

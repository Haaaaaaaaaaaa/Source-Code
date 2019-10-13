<%@ page contentType="text/html;charset=Gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>My JSP 'result.jsp' starting page</title>
  </head>
  
  <body bgcolor=#ccddoo>
  <%
  	String number=request.getParameter("number");
  	try{
  		double a=Double.parseDouble(number);
  		if(a<0){
  			response.setContentType("text/plain");
  			double num1=a*a;
  			out.println(a+"的平方是："+num1);
  		}
  		else if(a>0&&a<100){
  			response.setContentType("application/msword");
  			double num2=a*a*a;
  			out.println(a+"的立方是："+num2);
  		}
  		else if(a>=100.0){
  			response.setStatus(404);
  			out.println(a);
  		}
  	}
  	catch(Exception e){
  		response.sendRedirect("input.jsp");
  	}
   %>
  </body>
</html>

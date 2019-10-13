<%@ page contentType="text/html;charset=Gb2312"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'Computer.jsp' starting page</title>
  </head>
  
  <body bgcolor=#00beee>
  <%
  	String number1=request.getParameter("number1");
  	String number2=request.getParameter("number2");
  	String flag=request.getParameter("flag");
  	if(number1==null){
  		number1="0";
  	}
  	if(number2==null){
  		number2="0";
  	}  	
  	try{
  		double a=Double.parseDouble(number1);
  		double b=Double.parseDouble(number2);
  		double all=0;
  		if(flag.equals("+")){
  			all=a+b;
  		}
  		else if(flag.equals("-")){
  			all=a-b;
  		}
  		else if(flag.equals("*")){
  			all=a*b;
  		}
  		else if(flag.equals("/")){
  			all=a/b;
  		}
  		out.println(a+flag+b+"="+all);
  		}
  		catch(Exception e){
  			out.println("错误输入，请重新输入");
  		}
   %>
  </body>
</html>

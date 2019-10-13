<%@ page contentType="text/html;charset=Gb2312"%>
<!DOCTYPE HTML>
<html>
	<body bgcolor=cyan>
  	<%
  	String str=request.getParameter("guess");
  	if(str==null||str.length()==0){
  		response.sendRedirect("inputGuess.jsp");
  	}
  	else{
  		int guessNumber=Integer.parseInt(str);
  		session.setAttribute("gusess", new Integer(guessNumber));
  		Integer integer=(Integer)session.getAttribute("save");
  		int realnumber=integer.intValue();
  		if(guessNumber==realnumber){
  			int n=((Integer)session.getAttribute("count")).intValue();
  			n=n+1;
  			session.setAttribute("count", new Integer(n));
  			response.sendRedirect("success.jsp");
  		}
  		else if(guessNumber>realnumber){
  			int n=((Integer)session.getAttribute("count")).intValue();
  			n=n+1;
  			session.setAttribute("count", new Integer(n));
  			response.sendRedirect("large.jsp");
  		}
  		else if(guessNumber<realnumber){
  			int n=((Integer)session.getAttribute("count")).intValue();
  			n=n+1;
  			session.setAttribute("count", new Integer(n));
  			response.sendRedirect("small.jsp");
  		}
  	}
  	%>
	</body>
</html>

<%@ page contentType="text/html;charset=Gb2312"%>
<!DOCTYPE HTML>
<html>
	<body bgcolor=cyan>
    	随机分给了一个1到100之间的数，请猜。<br>
    	<%
    		int number=(int)(Math.random()*100)+1;
    		session.setAttribute("count", new Integer(0));
    		session.setAttribute("save", new Integer(number));
    	 %>
    	输入猜测：
    	<form action="resultServlet" method=post name=form>
    	<input type="text" name="guess">
    	<input type="submit" name=submit value="提交">
    	</form>
	</body>
</html>

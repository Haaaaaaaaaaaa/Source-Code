<%@ page contentType="text/html;charset=Gb2312"%>
<!DOCTYPE HTML>
<html>
	<body bgcolor=cyan>
    	����ָ���һ��1��100֮���������¡�<br>
    	<%
    		int number=(int)(Math.random()*100)+1;
    		session.setAttribute("count", new Integer(0));
    		session.setAttribute("save", new Integer(number));
    	 %>
    	����²⣺
    	<form action="resultServlet" method=post name=form>
    	<input type="text" name="guess">
    	<input type="submit" name=submit value="�ύ">
    	</form>
	</body>
</html>

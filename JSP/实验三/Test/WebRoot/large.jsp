<%@ page contentType="text/html;charset=Gb2312"%>
<!DOCTYPE HTML>
<html>
	<body bgcolor=cyan>
	<%
	Integer integer =(Integer)session.getAttribute("guess");
	 %>
	 <p><%=integer %>数大了，请再猜！
	 <form action="result.jsp" method=post name=form>
	 <input type="text" name=guess>
	 <input type="submit" name=submit value="提交">
	 </form>
	</body>
</html>

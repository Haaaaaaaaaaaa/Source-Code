<%@ page contentType="text/html;charset=Gb2312"%>
<!DOCTYPE HTML>
<html>
	<body bgcolor=#ff99ee>
	<% 
	Integer integer=(Integer)session.getAttribute("guess");
	%>
	<p><%= integer %>数小了，请再猜！
	<form action="resultServlet" method=post name=form>
		<input type="text" name="guess">
		<input type="submit" name=submit value="提交">
	</form>
	</body>
</html>

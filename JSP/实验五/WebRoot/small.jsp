<%@ page contentType="text/html;charset=Gb2312"%>
<!DOCTYPE HTML>
<html>
	<body bgcolor=#ff99ee>
	<% 
	Integer integer=(Integer)session.getAttribute("guess");
	%>
	<p><%= integer %>��С�ˣ����ٲ£�
	<form action="resultServlet" method=post name=form>
		<input type="text" name="guess">
		<input type="submit" name=submit value="�ύ">
	</form>
	</body>
</html>

<%@ page contentType="text/html;charset=Gb2312"%>
<!DOCTYPE HTML>
<html>
	<body bgcolor=cyan>
	<%
	Integer integer =(Integer)session.getAttribute("guess");
	 %>
	 <p><%=integer %>�����ˣ����ٲ£�
	 <form action="result.jsp" method=post name=form>
	 <input type="text" name=guess>
	 <input type="submit" name=submit value="�ύ">
	 </form>
	</body>
</html>

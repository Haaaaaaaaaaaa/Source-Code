<%@ page contentType="text/html;charset=Gb2312"%>
<!DOCTYPE HTML>
<html>
	<body bgcolor=cyan>
	<%
	int count=((Integer)session.getAttribute("count")).intValue();
	int num=((Integer)session.getAttribute("save")).intValue();
	%>
	<br>��ϲ�㣬�¶��ˣ�
	<br><b>�㹲����<%=count %>��
	<br>������־��ǣ�<%=num %>
	</body>
</html>

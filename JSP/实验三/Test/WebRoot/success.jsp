<%@ page contentType="text/html;charset=Gb2312"%>
<!DOCTYPE HTML>
<html>
	<body bgcolor=cyan>
	<%
	int count=((Integer)session.getAttribute("count")).intValue();
	int num=((Integer)session.getAttribute("save")).intValue();
	%>
	<br>恭喜你，猜对了！
	<br><b>你共猜了<%=count %>次
	<br>这个数字就是：<%=num %>
	</body>
</html>

<%@ page contentType="text/html;charset=gb2312"%>
<html>
<head>
<title>Login</title>
</head>
<body bgcolor=#99CCCC>
	<div align="center">
		<font size=3>
			<h1>��ע��</h1>
			<form action="AddServlet" method="post">
				<table>
					<tr>
						<td>�û�����</td>
						<td><input type="text" name="username"></td>
					</tr>
					<tr>
						<td>�� �룺</td>
						<td><input type="password" name="password"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="�ύ"></td>
					</tr>
				</table>
			</form>
		</font>
	</div>
	<script>
		var Null = '<%=request.getParameter("Null")%>';
		if (Null == 'yes') {
			alert("�û��������벻��Ϊ��!");
		}
	</script>
</body>
</html>

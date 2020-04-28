<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>MVC</title>
</head>
<body bgcolor="#E3E3E3">
	<!-- 表单这样提交，其实就是请求servlet -->
	<form action="login" method="post">
		<table>
			<caption>用户登录</caption>
			<tr>
				<td>登录名：</td>
				<td><input type="text" name="username" size=”20” /></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input type="password" name="pwd" size=”21” /></td>
			</tr>
		</table>
		<input type="submit" value="登录" /> <input type="reset" value="重置" />
	</form>
</body>
</html>


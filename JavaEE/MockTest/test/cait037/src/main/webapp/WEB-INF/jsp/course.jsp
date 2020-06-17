<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户信息</title>
</head>
<body>
	<table border=1>
		<tr>
			<td>课程id</td>
			<td>课程名称</td>
			<td>课程编号</td>
			<td>学时</td>
			<td>学分</td>
			<td>考试类型</td>
			<td>添加时间</td>
		</tr>
		<tr>
			<td>${c.id}</td>
			<td>${c.coursename}</td>
			<td>${c.coursenum}</td>
			<td>${c.coursehour}</td>
			<td>${c.credit}</td>
			<td>${c.examtype}</td>
			<td>${c.addtime}</td>
		</tr>
	</table>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="addACourse" method="post">
	课程名称：<input type="text" name="coursename"/><br>
	课程编号：<input type="text" name="coursenum"/><br>
	学时：<input type="text" name="coursehour"/><br>
	学分：<input type="text" name="credit"/><br>
	考试类型：<input type="text" name="examtype"/><br>
	<input type="submit" name="submit" value="添加"> 
</form>
</body>
</html>
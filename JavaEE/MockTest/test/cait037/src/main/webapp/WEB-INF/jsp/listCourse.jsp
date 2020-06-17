<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table class="table table-bordered table-striped" border="1">
						<thead>
							<tr>
								<td>课程id</td>
								<td>课程名称</td>
								<td>课程编号</td>
								<td>学时</td>
								<td>学分</td>
								<td>考试类型</td>
								<td>添加时间</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="c">
								<tr>
									<td>${c.id}</td>
									<td>${c.coursename}</td>
									<td>${c.coursenum}</td>
									<td>${c.coursehour}</td>
									<td>${c.credit}</td>
									<td>${c.examtype}</td>
									<td>${c.addtime}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
</body>
</html>
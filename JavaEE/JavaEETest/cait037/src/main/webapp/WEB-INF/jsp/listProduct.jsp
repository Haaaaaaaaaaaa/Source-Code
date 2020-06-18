<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
								<th>主键</th>
								<th>产品全称</th>
								<th>产品编号</th>
								<th>产品类别</th>
								<th>备注</th>
								<th>添加时间</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="row">
								<tr>
									<td>${row.id}</td>
									<td>${row.productname}</td>
									<td>${row.productnum}</td>
									<td>${row.producttype}</td>
									<td>${row.remark}</td>
									<td>${row.addtime}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
</body>
</html>
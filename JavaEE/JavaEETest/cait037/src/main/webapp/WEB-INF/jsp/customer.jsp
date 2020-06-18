<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<td>主键</td>
			<td>产品全称</td>
			<td>产品编号</td>
			<td>产品类别</td>
			<td>备注</td>
			<td>添加时间</td>
		</tr>
		<tr>
			<td>${product.id}</td>
			<td>${product.productname}</td>
			<td>${product.productnum}</td>
			<td>${product.producttype}</td>
			<td>${product.remark}</td>
			<td>${product.addtime}</td>
		</tr>
	</table>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="addProduct" method="post">
主键：<input type="text" name="id"/><br>
产品全称：<input type="text" name="productname"/><br>
产品编号：<input type="text" name="productnum"/><br>
产品类别：<input type="text" name="producttype"/><br>
备注：<input type="text" name="remark"/><br>
添加时间：<input type="text" name="addtime"/><br>
<input type="submit" value="添加"/><br>
</form>
</body>
</html>
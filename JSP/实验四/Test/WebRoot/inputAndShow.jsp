<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>
<jsp:useBean id="Get" class="ujn.GetArea" scope="request"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>计算三角形或梯形面积</title>
  </head>
  
  
  <body bgcolor="cyan">
  	<form action="" method="post">
  	请输入三条边：
  	边A:<input type="text" name="a" size=5>
  	边B:<input type="text" name="b" size=5>
  	边C:<input type="text" name="c" size=5>	
  	<input type="submit" name="submit" value="提交">	
  	</form>
  	<jsp:setProperty name="Get" property="*"/>
  	三边为：
  	<jsp:getProperty name="Get" property="a"/>
  	<jsp:getProperty name="Get" property="b"/>
  	<jsp:getProperty name="Get" property="c"/>
  	<br>
  	<b>这三条边可以构成一个三角形吗？<jsp:getProperty name="Get" property="triangle"/>
  	<br>
  	面积是：<jsp:getProperty name="Get" property="area"/>
  	<br>
  	这三条边构成一个梯形
  	<br>
  	面积是：<jsp:getProperty name="Get" property="area2"/>
  	</b>
  </body>
</html>

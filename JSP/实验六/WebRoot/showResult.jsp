<%@ page contentType="text/html; charset=Gb2312" %>
<jsp:useBean id="data" type="model.Area" scope="session"/>
<html>
  <head>   
    <title>showResult</title>
  </head>
  
  <body bgcolor=cyan>
  是否构成三角形：<jsp:getProperty name="data" property="mess"/><br>
    三角形面积是：<jsp:getProperty name="data" property="area1"/><br>
    梯形面积是：<jsp:getProperty name="data" property="area2"/> 
  </body>
</html>

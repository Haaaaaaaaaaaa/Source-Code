<%@ page contentType="text/html; charset=Gb2312" %>
<jsp:useBean id="data" type="model.Area" scope="session"/>
<html>
  <head>   
    <title>showResult</title>
  </head>
  
  <body bgcolor=cyan>
  �Ƿ񹹳������Σ�<jsp:getProperty name="data" property="mess"/><br>
    ����������ǣ�<jsp:getProperty name="data" property="area1"/><br>
    ��������ǣ�<jsp:getProperty name="data" property="area2"/> 
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>
<jsp:useBean id="Get" class="ujn.GetArea" scope="request"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>���������λ��������</title>
  </head>
  
  
  <body bgcolor="cyan">
  	<form action="" method="post">
  	�����������ߣ�
  	��A:<input type="text" name="a" size=5>
  	��B:<input type="text" name="b" size=5>
  	��C:<input type="text" name="c" size=5>	
  	<input type="submit" name="submit" value="�ύ">	
  	</form>
  	<jsp:setProperty name="Get" property="*"/>
  	����Ϊ��
  	<jsp:getProperty name="Get" property="a"/>
  	<jsp:getProperty name="Get" property="b"/>
  	<jsp:getProperty name="Get" property="c"/>
  	<br>
  	<b>�������߿��Թ���һ����������<jsp:getProperty name="Get" property="triangle"/>
  	<br>
  	����ǣ�<jsp:getProperty name="Get" property="area"/>
  	<br>
  	�������߹���һ������
  	<br>
  	����ǣ�<jsp:getProperty name="Get" property="area2"/>
  	</b>
  </body>
</html>

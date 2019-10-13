<%@ page contentType="text/html;charset=Gb2312"%>
<!DOCTYPE HTML >
<html>
  <head> 
    <title>My JSP 'inputNumber.jsp' starting page</title>
  </head>
  
  <body bgcolor=cyan>
  	输入运算数、选择运算符： <br>
  	<form action="Computer.jsp" method=post>
  	<input type="text" name="number1" size=6>
 	 	<select name="flag">
  		<option value="+">+</option>
  		<option value="-">-</option>
		<option value="*">*</option>
		<option value="/">/</option>
  		</select>
  	<input type="text" name="number2" size=6><br>
	<input type="submit" name="submit" size=6 value="提交你的选择">
  	</form>
  </body>
</html>

<%@ page contentType="text/html;charset=gb2312" %>
<html>
  <head>
    <title>Login</title>
  </head>
  <body bgcolor=cyan>
  <div align="center">
  <font size=3>
    <h1>���¼</h1>
    <form action="loginServlet" method="post">
    <table>
        <tr>
        	<td>�û�����</td>
        	<td><input type="text" name="username"></td>
        </tr>
        <tr>
        	<td>�� �룺</td>	
        	<td><input type="password" name="password"></td>
        </tr>
        <tr>
        	<td></td>
    		<td><input type="submit" value="�ύ"></td>
    	</tr>
    </table>
    </form>
    </font>
    </div>
  </body>
</html>
<%@ page contentType="text/html;charset=gb2312" %>
<html>
  <head>
    <title>Login</title>
  </head>
  <body bgcolor=#CCCC33>
  <div align="center">
  <font size=3>
    <h1>请登录</h1>
    <form action="loginServlet" method="post">
    <table>
        <tr>
        	<td>用户名：</td>
        	<td><input type="text" name="username"></td>
        </tr>
        <tr>
        	<td>密 码：</td>	
        	<td><input type="password" name="password"></td>
        </tr>
        <tr>
        	<td></td>
    		<td><input type="submit" value="提交"></td>
    	</tr>
    </table>
    </form>
   
   
    </div>
    </font>
    </div>
  </body>
</html>

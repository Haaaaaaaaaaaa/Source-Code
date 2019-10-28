<%@ page contentType="text/html;charset=gb2312" %>
<html>
  <head>
    <title>Login</title>
  </head>
  <style>
  a{padding:20px;}
  </style>
  <body bgcolor=cyan>
  <div align="center">
  <font size=3>
    <h1>请注册</h1>
    <form action="add" method="post">
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
    </font>
    </div>
  </body>
</html>

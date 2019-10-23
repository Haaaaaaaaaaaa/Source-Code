<%@ page contentType="text/html;charset=utf-8" %>
<html>
  <head>
    <title>Login</title>
  </head>
  
  <body bgcolor=cyan>
        欢迎登录<br>
    <form action="loginServlet" method="post">
        用户名：<input type="text" name="username">
        密 码：	<input type="password" name="pwd">
    		<input type="submit" value="提交">
    </form>
  </body>
</html>

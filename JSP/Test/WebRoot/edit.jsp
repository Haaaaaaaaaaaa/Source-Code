<%@ page import="model.User" %>
<%@ page import="service.UserService" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>编辑</title>
</head>
<body bgcolor=cyan>
<%
    UserService userService = new UserService();
    int id = Integer.parseInt(request.getParameter("id"));
    User user = userService.Search(id);
    String username = user.getUsername();
    String password = user.getPassword();
%>
<h3 align="center">修改用户<%=username%>的数据</h3>
<div align="center">
<form action="EditServlet" method="post" >
    <input type="hidden" name="id" value="<%=id%>"/>
    <table>
        <tr>
        	<td>用户名：</td>
        	<td><input type="text" name="username" value="<%=username%>" size="5"/></td>
        </tr>
        <tr>
        	<td>密 码：</td>	
        	<td><input type="text" name="password" value="<%=password%>" size="5"/></td>
        </tr>
        <tr>
        	<td></td>
    		<td><input type="submit" value="修改"></td>
    	</tr>
    </table>
</form>
</div>
<script>
    var edit = '<%=request.getParameter("edit")%>';
    if (edit == "no") {
        alert("修改失败！");
    }

</script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8"%>
<jsp:useBean id="bean" class="model.User" scope="session"/>
<html>
<head>
    <title>编辑</title>
</head>
<body bgcolor=#CCCC00>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    String username = request.getParameter("username");
    String password = request.getParameter("password");
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
    		<td><input type="submit" value="确认修改"></td>
    	</tr>
    </table>
</form>
</div>
</body>
</html>

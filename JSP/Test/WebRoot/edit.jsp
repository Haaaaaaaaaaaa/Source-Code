<%@ page contentType="text/html;charset=gb2312"%>
<jsp:useBean id="bean" class="model.User" scope="session"/>
<html>
<head>
    <title>�༭</title>
</head>
<body bgcolor=#CCCC00>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    String username = request.getParameter("username");
    String password = request.getParameter("password");
%>
<h3 align="center">�޸��û�<%=username%>������</h3>
<div align="center">
<form action="EditServlet" method="post" >
    <input type="hidden" name="id" value="<%=id%>"/>
    <table>
        <tr>
        	<td>�û�����</td>
        	<td><input type="text" name="username" value="<%=username%>" size="5"/></td>
        </tr>
        <tr>
        	<td>�� �룺</td>	
        	<td><input type="text" name="password" value="<%=password%>" size="5"/></td>
        </tr>
        <tr>
        	<td></td>
    		<td><input type="submit" value="ȷ���޸�"></td>
    	</tr>
    </table>
</form>
</div>
</body>
</html>

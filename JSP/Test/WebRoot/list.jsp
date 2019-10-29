<%@ page contentType="text/html;charset=UTF-8"%>
<jsp:useBean id="bean" class="model.User" scope="session"/>
<html>
<head>
    <title>查询</title>
</head>
<body bgcolor=cyan>
<h2 align="center">用户信息如下：</h2>
<table align="center" border=3>
<% String [] columnName=bean.getColumnName(); %>
		<tr>
		<% for(String s:columnName){
		 %>  <th><%=s%></th>
		 <%}
		 %>	</tr>
		 <% String [][]record=bean.getTableRecord(); 
		 for(int i=0;i<record.length;i++){
		 %>  <tr>
		 <% for(int j=0;j<record[i].length;j++){
		  %>  <td><%=record[i][j]%></td>
		  <%
		  }%>
		   <td><button onclick="window.location.href='edit.jsp?id='+<%=record[i][0]%>">修改</button></td>
       	   <td><button onclick="window.location.href = 'http://localhost:8888/JSP_war_exploded/delete?id='+<%=record[i][0]%>">删除</button></td>
		  </tr>
		  <%}
		  %>
	 </table>
   </body>
</html>

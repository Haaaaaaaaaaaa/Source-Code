<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<html>
<head>
<title>RESTful测试</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
function search(){
	// 获取输入的查询编号
	var id = $("#number").val();		
	$.ajax({
		url : "${pageContext.request.contextPath }/user/"+id,
		type : "GET", 
		//定义回调响应的数据格式为JSON字符串,该属性可以省略
		dataType : "json",
		//成功响应的结果
		success : function(data){
			if(data.username != null){	
			    alert("您查询的用户是："+data.username);
			}else{
			    alert("没有找到id为:"+id+"的用户！");
			}
		}
	});
}
</script>
</head>
<body>
    <form>
         编号：<input type="text" name="number" id="number">
	    <input type="button" value="搜索" onclick="search()" />
    </form> 
</body>
</html>

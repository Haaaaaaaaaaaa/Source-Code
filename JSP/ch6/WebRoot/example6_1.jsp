<%@ page contentType="text/html;charset=gb2312" %>
<HTML><body bgcolor=#FFBBFF>
<font size=2>

<form action="computerSum" method="post" >
 等差数列求和：
  <BR>
  	输入首项:<input type=text name="firstItem" size=4>
      输入公差:<input type=text name="var" size=4>
      求和项数:<input type=text name="number" size=4>
  <input type=submit value="提交">
</form>

<form action="computerSum" method="get" >
   等比数列求和：
  <br>
  输入首项:<input type=text name="firstItem" size=4>
  输入公比:<input type=text name="var" size=4>
  求和项数:<input type=text name="number" size=4>
  <input type=submit value="提交">
</form>

</font>
</body>
</HTML>

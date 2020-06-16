<%--
  Created by IntelliJ IDEA.
  User: HZ
  Date: 2020/3/31
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
   我是ex01.jsp页面
<%
    String id=session.getId();
     out.print("<br>你的session对象的ID事：</br>"+id);
%>
      <br>连接到ex00.jsp的页面。<br>
<a href="ex00.jsp">ex00.jsp</a>
</body>
</html>

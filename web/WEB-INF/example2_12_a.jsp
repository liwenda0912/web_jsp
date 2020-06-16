<%--
  Created by IntelliJ IDEA.
  User: HZ
  Date: 2020/3/17
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body bgcolor=cyan>
<% String s=request.getParameter("number");
    out.println("传递过来的值是"+s);
%>
<br><img src=image/meigui.jpg width=120 height=120/>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: HZ
  Date: 2020/3/17
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1> 产生一个1-10之间的随机数
        <%  double i=(int)(Math.random()*10)+1;
    if(i<=5) {
%>       <jsp:forward page="example2_12_a.jsp" >
        <jsp:param name="number" value="<%= i %>" />
    </jsp:forward>
        <%  }
    else {
 %>     <jsp:forward page="example2_12_b.jsp" >
        <jsp:param name="number" value="<%= i %>" />
    </jsp:forward>
        <% }
 %>
</body>
</html>

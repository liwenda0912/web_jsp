<%--
  Created by IntelliJ IDEA.
  User: HZ
  Date: 2020/3/17
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GB2312" %>
<html>
<head>
    <title>Title</title>
</head>
<body  bgcolor=cyan >
<% double a=3,b=4,c=5;
%>
<br>����trangle.jsp��������Ϊ<%=a%>,<%=b%>,<%=c%>�����������.
<jsp:include page="myfile/trangle.jsp">
    <jsp:param name="sideA" value="<%=a%>"/>
    <jsp:param name="sideB" value="<%=b%>"/>
    <jsp:param name="sideC" value="<%=c%>"/>
</jsp:include>
</body>
</html>

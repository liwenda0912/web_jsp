<%--
  Created by IntelliJ IDEA.
  User: HZ
  Date: 2020/3/31
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
      我是ex00.jsp页面
<%  String id =session.getId();
    out.print("<br>您的session对象的ID是：</br>"+id);
%>
    <form action="ex01.jsp" method="post" name="one">
        <input type="text" name="txt1">
        <input type="submit" value="送出" name="submit">
    </form>

</body>
</html>

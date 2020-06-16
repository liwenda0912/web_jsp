<%--
  Created by IntelliJ IDEA.
  User: HZ
  Date: 2020/3/20
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<HEAD>
    <%@include file = "head.txt"%>
</HEAD>
<HTML>
<BODY bgcolor = red>

<P><Font size = 5  color =black> This is errror.jsp </Font>
    <Font size = 2>
        <%   String s= request.getParameter("mess");
            out.println("<BR>传递过来的值是"+ s);
        %>
        <BR><img src = "error. jsp" width = "120%>" height = "120"></img>
    </Font></BODY></HTML>
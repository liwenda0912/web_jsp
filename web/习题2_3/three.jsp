<%--
  Created by IntelliJ IDEA.
  User: HZ
  Date: 2020/3/20
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<HEAD>
    <%@include file = "head.txt"%>
</HEAD>
<HTML>
<BODY bgcolor = blue>

<P><Font size = 2  color = red> This is three.jsp </Font>
    <Font size = 3>
        <%   String s = request.getParameter("number");
            out.println("<BR>传递过来的值是"+ s);
        %>
        <BR><img src = "b.jsp"  width = "<%= s%>"  height = "<%= s%>"></img>
    </Font></BODY></HTML>

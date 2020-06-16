<%--
  Created by IntelliJ IDEA.
  User: HZ
  Date: 2020/3/20
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<HEAD>
    <%@include file = "head.txt"%>
</HEAD>
<HTML>
<BODY bgcolor = yellow>
<FORM action ="" method =get  name=form>

    请输入1~100之间的整数：<INPUT type="text" name="number">
    <BR><INPUT TYPE="submit" value="送出" name=submit >
    </FORM>
<% String num=request.getParameter("number");
      if(num == null)
              num="0";
      try{ int n=Integer.parseInt(num);
             if(n>= 1&&n<= 50){
%>  <jsp:forward page = "two.jsp">
       <jsp:param name = "number" value ="<%=n%>"/>
    </jsp:forward>
<%    }
        else if (n > 50&&n <= 100){
%>   <jsp:forward page = "three.jsp">
         <jsp:param name = "number"  value = "<%=n%>"/>
    </jsp:forward>
<%    }
        else if (n>100){
%>  <jsp:forward page = "error.jsp">
        <jsp:param name = "mess"  value = "<%=n%>"/>
    </jsp:forward>
        <%    }
    }
      catch (Exception e){
%>   <jsp:forward page="error.jsp">
       <jsp:param name="mess" value="<%=e.toString()%>"/>
    </jsp:forward>
<%    }
%>
</BODY></HTML>
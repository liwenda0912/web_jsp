<%@ page contentType="text/html;charset=gb2312" %>
<HTML><body  bgcolor=#EECCFF>
   我是example3_14_b.jsp页面
   <%  String id=session.getId();
       out.println("<br>您的session对象的ID是：<br>"+id);
       String str = response.encodeRedirectURL("example3_14_a.jsp");
   %>
<BR> 连接到example3_14_a.jsp的页面。<br>
<a href="<%=str %>"><%=str %></A>
</body></HTML>


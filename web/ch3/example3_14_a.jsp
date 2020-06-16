<%@ page contentType="text/html;charset=gb2312" %>
<HTML><body  bgcolor=cyan>
   我是example3_14_a.jsp页面<br>输姓名连接到example3_14_b.jsp
   <% String id=session.getId();
      out.println("<br>您的session对象的ID是：<br>"+id);
      String str = response.encodeRedirectURL("example3_14_b.jsp");
   %>
  <form action="<%=str %>" method=post name=form>
       <input type="text" name="boy"> 
       <input type="submit" value="送出" name=submit>
  </form>  
</body></HTML>


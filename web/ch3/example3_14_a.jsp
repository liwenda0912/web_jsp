<%@ page contentType="text/html;charset=gb2312" %>
<HTML><body  bgcolor=cyan>
   ����example3_14_a.jspҳ��<br>���������ӵ�example3_14_b.jsp
   <% String id=session.getId();
      out.println("<br>����session�����ID�ǣ�<br>"+id);
      String str = response.encodeRedirectURL("example3_14_b.jsp");
   %>
  <form action="<%=str %>" method=post name=form>
       <input type="text" name="boy"> 
       <input type="submit" value="�ͳ�" name=submit>
  </form>  
</body></HTML>


<%@ page contentType="text/html;charset=gb2312" %>
<HTML><body  bgcolor=#EECCFF>
   ����example3_14_b.jspҳ��
   <%  String id=session.getId();
       out.println("<br>����session�����ID�ǣ�<br>"+id);
       String str = response.encodeRedirectURL("example3_14_a.jsp");
   %>
<BR> ���ӵ�example3_14_a.jsp��ҳ�档<br>
<a href="<%=str %>"><%=str %></A>
</body></HTML>


<%--
  Created by IntelliJ IDEA.
  User: HZ
  Date: 2020/5/26
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.PrintWriter" %>
<html>
<head>
    <title>Title</title>
</head>
<body bgcolor=#EEDDFF>
<% Connection con;
    Statement sql;
    ResultSet rs;
    try{  Class.forName("com.mysql.jdbc.Driver");
    }
    catch(Exception e){
       out.println("忘记把MySQL数据库的JDBC-数据库驱动程序复制到JDK的扩展目录中");
    }
    try { String uri= "jdbc:mysql://127.0.0.1/warehouse";
          String user="root";
          String password="";
          con=DriverManager.getConnection(uri,user,password);
          //也可以写成con=DriverManager.getConnection(uri+"?user=root&password=");
          sql=con.createStatement();
          rs=sql.executeQuery("SELECT * FROM produce ");
          out.print("<table border=2>");
          out.print("<tr>");
          out.print("<th width=100>"+"产品号"+"</th>");
          out.print("<th width=100>"+"名称"+"</th>");
          out.print("<th width=50>"+"生产日期"+"</th>");
          out.print("<th width=50>"+"价格"+"</th>");
          out.print("</TR>");
          while(rs.next()){
             out.print("<tr>");
              out.print("<td >"+rs.getString(1)+"</td>");
              out.print("<td >"+rs.getString(2)+"</td>");
              out.print("<td >"+rs.getString(3)+"</td>");
              out.print("<td >"+rs.getFloat(4)+"</td>");
              out.print("</tr>") ;
          }
          out.print("</table>");
          rs.close();
          sql.close();
          con.close();
    }
    catch(SQLException e){
          out.print(e);
    }
 %>

</body>
</html>

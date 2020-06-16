package classes.myservlet.control;
import mybean.data.Example7_8_Bean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Example7_8_Servlet extends HttpServlet{
   public void init(ServletConfig config) throws ServletException{
      super.init(config);
      try {  Class.forName("com.mysql.jdbc.Driver");
      }
      catch(Exception e){} 
   }
   public void doPost(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException{
      request.setCharacterEncoding("gb2312");
      String dataBase= request.getParameter("dataBase");
      String tableName= request.getParameter("tableName");
      String user= request.getParameter("user");
      String password= request.getParameter("password");
      boolean boo =( dataBase==null||dataBase.length()==0);
      boo = boo||( tableName==null||tableName.length()==0);
      boo = boo||( user==null||user.length()==0);
      if(boo) {
         fail(request,response,"��ѯʧ��");
      }
      HttpSession session=request.getSession(true); 
      Connection con=null; 
      Example7_8_Bean pageBean=null;
      try{ 
           pageBean=(Example7_8_Bean)session.getAttribute("pageBean");
           if(pageBean==null){
              pageBean=new Example7_8_Bean();  //����Javabean����
              session.setAttribute("pageBean",pageBean);
           }
      }
      catch(Exception exp){
           pageBean=new Example7_8_Bean();  
           session.setAttribute("pageBean",pageBean);
      } 
      String uri="jdbc:mysql://127.0.0.1/"+dataBase;
      try{ 
          con=DriverManager.getConnection(uri,user,password);
          Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);
          ResultSet rs=sql.executeQuery("SELECT * FROM "+tableName);
          ResultSetMetaData metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount(); //�õ������������
          String []columnName = new String[columnCount];
          for(int i=0;i<columnName.length;i++) {
             columnName[i] = metaData.getColumnName(i+1); //�õ�����
          }
          pageBean.setColumnName(columnName);   //����Javabean����ģ��
          rs.last();
          int rowNumber=rs.getRow();  //�õ���¼��
          String [][] tableRecord=pageBean.getTableRecord();
          tableRecord = new String[rowNumber][columnCount];
          rs.beforeFirst();
          int i=0;
          while(rs.next()){
            for(int k=0;k<columnCount;k++) 
              tableRecord[i][k] = rs.getString(k+1);
              i++; 
          }
          pageBean.setTableRecord(tableRecord); //����Javabean����ģ��
          con.close();
          response.sendRedirect("example7_8_pageShow.jsp");  //�ض���
     }
     catch(SQLException e){
          System.out.println(e);
     }  
   }
   public  void  doGet(HttpServletRequest request,HttpServletResponse response)
           throws ServletException,IOException{
       doPost(request,response);
   }
   public void fail(HttpServletRequest request,HttpServletResponse response,
                      String backNews) {
        response.setContentType("text/html;charset=GB2312");
        try {
         PrintWriter out=response.getWriter();
         out.println("<html><body>");
         out.println("<h2>"+backNews+"</h2>") ;
         out.println("����");
         out.println("<a href =example7_8.jsp>������ȷ��Ϣ</a>");
         out.println("</body></html>");
        }
        catch(IOException exp){} 
  }

}

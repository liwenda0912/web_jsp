package classes.myservlet.control;

import mybean.data.Example7_2_Bean;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "Example7_3_Servlet",urlPatterns={"/insertServlet"},loadOnStartup = 1)
public class Example7_3_Servlet extends HttpServlet {
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Example7_2_Bean resultBean=null;
        try{  resultBean=(Example7_2_Bean)request.getAttribute("resultBean");
            if(resultBean==null){
                resultBean=new Example7_2_Bean(); //创建Javabean对象
                request.setAttribute("resultBean",resultBean);
            }
        }
        catch(Exception exp){
            resultBean=new Example7_2_Bean();  //创建Javabean对象
            request.setAttribute("resultBean",resultBean);
        }
        try{  Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){}
        request.setCharacterEncoding("utf-8");
        String dataBase = request.getParameter("dataBase");
        String tableName = request.getParameter("tableName");
        String nu=request.getParameter("number");
        String na=request.getParameter("name");
        String mT=request.getParameter("madeTime");
        String pr=request.getParameter("price");
        if(nu==null||nu.length()==0) {
            fail(request,response,"添加记录失败，必须给出记录");
            return;
        }
        float p=Float.parseFloat(pr);
        //String condition="DELETE FROM " +tableName+" WHERE NUMBER='"+nu+"'";//删除
        String condition=" UPDATE "+tableName+" SET name='"+na+"'WHERE number='"+nu+"'";
        //String condition = "INSERT IN "+tableName+" VALUES"+
                //"("+"'"+nu+"','"+na+"','"+mT+"',"+p+")";
        Connection con;
        Statement sql;
        ResultSet rs;
        try{
            String uri="jdbc:mysql://127.0.0.1/"+dataBase+"?"+
                    "user=root&password=&characterEncoding=utf-8";
            con=DriverManager.getConnection(uri);//与数据库进行连接
            sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            sql.executeUpdate(condition);
            rs=sql.executeQuery("SELECT * FROM "+tableName);
            ResultSetMetaData metaData = rs.getMetaData();//获得数据库的列名
            int columnCount = metaData.getColumnCount(); ////获得数据库的列数
            String []columnName = new String[columnCount];//设定列数的大小
            for(int i=0;i<columnName.length;i++) {
                columnName[i] = metaData.getColumnName(i+1); //将列名存放columnname里
            }
            resultBean.setColumnName(columnName);   //将存放列名的columnname存放在javabeen里
            rs.last();
            int rowNumber=rs.getRow();  //获取数据库的行数
            String [][] tableRecord=resultBean.getTableRecord();
            tableRecord = new String[rowNumber][columnCount];
            rs.beforeFirst();//从第一行的头开始
            int i=0;
            while(rs.next()){
                for(int k=0;k<columnCount;k++)
                    tableRecord[i][k] = rs.getString(k+1);//将数据一一存放进tablerecord里
                i++;
            }
            resultBean.setTableRecord(tableRecord); //将数据存放在Javabeen里
            rs.close();
            sql.close();
            con.close();
            RequestDispatcher dispatcher=
                    request.getRequestDispatcher("showRecord.jsp");
            dispatcher.forward(request,response);
        }
        catch(SQLException e){
            System.out.println(e);
            fail(request,response,"添加记录失败:"+e.toString());
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

   public void fail(HttpServletRequest request,HttpServletResponse response,
                      String backNews) {
        response.setContentType("text/html;charset=utf-8");
        try {
            response.setCharacterEncoding("utf-8");
         PrintWriter out=response.getWriter();
         out.println("<html><body>");
         out.println("<h2>"+backNews+"</h2>") ;
         out.println("返回");
         out.println("<a href =example7_3.jsp>输入记录¼</a>");
         out.println("</body></html>");
        }
        catch(IOException exp){} 
    }
}

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
import java.sql.*;

@WebServlet(name = "s_Servlet",urlPatterns={"/queryByConditionServlet"},loadOnStartup = 1)
public class s_Servlet extends HttpServlet {
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Example7_2_Bean resultBean=null;
        try{  resultBean=(Example7_2_Bean)request.getAttribute("resultBean");
            if(resultBean==null){
                resultBean=new Example7_2_Bean(); //����Javabean����
                request.setAttribute("resultBean",resultBean);
            }
        }
        catch(Exception exp){
            resultBean=new Example7_2_Bean();  //����Javabean����
            request.setAttribute("resultBean",resultBean);
        }
        try{  Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){}
        String number = request.getParameter("price");
        if(number==null||number.length()==0)
            return;
        String dataBase = request.getParameter("dataBase");
        String tableName = request.getParameter("tableName");
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        float p = Float.parseFloat(number);
        Connection con;
        Statement sql;
        ResultSet rs;
        try{
            String uri="jdbc:mysql://127.0.0.1/"+dataBase;
            con= DriverManager.getConnection(uri,user,password);
            sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String condition ="SELECT * FROM "+tableName+" where price > "+p;
            rs=sql.executeQuery(condition);
            ResultSetMetaData metaData = rs.getMetaData();//获得数据库的列名
            int columnCount = metaData.getColumnCount(); //获得数据库的列数
            String []columnName = new String[columnCount];//设定大小
            for(int i=0;i<columnName.length;i++) {
                columnName[i] = metaData.getColumnName(i+1); //将列名存放columnname里
            }
            resultBean.setColumnName(columnName);   //将存放列名的columnname存放在javabeen里
            rs.last();
            int rowNumber=rs.getRow();  //获取数据库的行数
            String [][] tableRecord=resultBean.getTableRecord();
            tableRecord = new String[rowNumber][columnCount];//设定大小
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
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}

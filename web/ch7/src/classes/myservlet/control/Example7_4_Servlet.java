package classes.myservlet.control;
import mybean.data.Example7_2_Bean; //��������2�е�Javabeanģ��
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.Calendar;


@WebServlet(name = "Example7_4_Servlet",urlPatterns={"/insertServlet"},loadOnStartup = 1)
public class Example7_4_Servlet extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Example7_2_Bean resultBean = null;
        try {
            resultBean = (Example7_2_Bean) request.getAttribute("resultBean");
            if (resultBean == null) {
                resultBean = new Example7_2_Bean(); //����Javabean����
                request.setAttribute("resultBean", resultBean);
            }
        } catch (Exception exp) {
            resultBean = new Example7_2_Bean();  //����Javabean����
            request.setAttribute("resultBean", resultBean);
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
        }
        request.setCharacterEncoding("gb2312");
        String dataBase = request.getParameter("dataBase");
        String tableName = request.getParameter("tableName");
        String number = request.getParameter("number");
        String name = request.getParameter("name");
        String madeTime = request.getParameter("madeTime");
        String pr = request.getParameter("price");
        if (number == null || number.length() == 0) {
            fail(request, response, "添加记录失败，必须给出记录");
            return;
        }
        float price = Float.parseFloat(pr);
        Connection con;
        Statement sql;
        ResultSet rs;
        try {
            String uri = "jdbc:mysql://127.0.0.1/" + dataBase + "?" +
                    "user=root&password=&characterEncoding=gb2312";
            con = DriverManager.getConnection(uri);
            sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = sql.executeQuery("SELECT * FROM " + tableName);
            rs.moveToInsertRow();      //rs的游标移动到插入行
            rs.updateString(1, number); //更新结果
            rs.updateString(2, name); //更新结果集
            String[] str = madeTime.split("[-/]");//输入的日期必须用-或/格式
            int year = Integer.parseInt(str[0]);
            int month = Integer.parseInt(str[1]);
            int day = Integer.parseInt(str[2]);
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, day);
            Date date = new java.sql.Date(calendar.getTimeInMillis());
            rs.updateDate(3, date);     //更新结果集
            rs.updateDouble(4, price);  //更新结果集
            rs.insertRow();     //������һ�м�¼
            rs = sql.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount(); //获取列数
            String[] columnName = new String[columnCount];
            for (int i = 0; i < columnName.length; i++) {
                columnName[i] = metaData.getColumnName(i + 1); //存放列名
            }
            resultBean.setColumnName(columnName);   //将列名存放进javabeen
            rs.last();
            int rowNumber = rs.getRow();  //获取行数
            String[][] tableRecord = resultBean.getTableRecord();
            tableRecord = new String[rowNumber][columnCount];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                for (int k = 0; k < columnCount; k++)
                    tableRecord[i][k] = rs.getString(k + 1);
                i++;
            }
            resultBean.setTableRecord(tableRecord); //将行的数据存放进javabeen
            con.close();
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("showRecord.jsp");
            dispatcher.forward(request, response);  //ת��
        } catch (SQLException e) {
            System.out.println(e);
            fail(request, response, "添加记录失败:" + e.toString());
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void fail(HttpServletRequest request, HttpServletResponse response,
                     String backNews) {
        response.setContentType("text/html;charset=utf-8");
        try {
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>" + backNews + "</h2>");
            out.println("返回");
            out.println("<a href =example7_3.jsp>输入记录¼</a>");
            out.println("</body></html>");
        } catch (IOException exp) {
        }
    }
}

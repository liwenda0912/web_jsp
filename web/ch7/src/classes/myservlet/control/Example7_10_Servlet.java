package classes.myservlet.control;
import mybean.data.Example7_10_Bean; //����Javabeanģ��
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class Example7_10_Servlet extends HttpServlet{
   public void init(ServletConfig config) throws ServletException{
      super.init(config);
   }
   public void doPost(HttpServletRequest request,HttpServletResponse response)
               throws ServletException,IOException{
      Example7_10_Bean testBean=null;
      HttpSession session=request.getSession(true);
      try{  testBean=(Example7_10_Bean)session.getAttribute("testBean");
            if(testBean==null){
                testBean=new Example7_10_Bean(); //����Javabean����
                session.setAttribute("testBean",testBean);
            }
      }
      catch(Exception exp){
            testBean=new Example7_10_Bean();  //����Javabean����
            session.setAttribute("testBean",testBean);
      } 
     try{  Class.forName("com.mysql.jdbc.Driver");
     }
     catch(Exception e){} 
     request.setCharacterEncoding("gb2312");
     String id=request.getParameter("id");
     if(id==null||id.length()==0) {
        notify(request,response,"�������ѧ��");
        return;
     }
     testBean.setId(id); 
     int testAmount = testBean.getTestAmount();   //��������
     Connection con;
     Statement sql; 
     ResultSet rs;
     try{ 
          String uri="jdbc:mysql://127.0.0.1/school?"+
                      "user=root&password=&characterEncoding=gb2312";
          con=DriverManager.getConnection(uri);
          sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                 ResultSet.CONCUR_READ_ONLY);
          rs=sql.executeQuery("SELECT * FROM test");
          rs.last();    
          int recordAmount=rs.getRow();  //�õ���¼��
          testAmount = Math.min(recordAmount,testAmount);
          LinkedList<Integer> list=(LinkedList<Integer>)session.getAttribute("list");
          if(list==null||list.size()==0){
             list = new LinkedList<Integer>();
             for(int i=1;i<=recordAmount;i++) {
               list.add(i);
             }
             session.setAttribute("list",list); 
          }
          int m= -1;
          int index=-1;
          if(list.size()>=1) {
             m= (int)(Math.random()*list.size());
             index=list.get(m);
             list.remove(m);
             session.setAttribute("list",list); 
             int tihao=testBean.getNumber();
             if(tihao<testAmount) {
                 //�����ж���һ���Ƿ���ȷ������������
                String studentAnswer=testBean.getAnswer();
                if(studentAnswer!=null&&studentAnswer.length()>=1) {
                   if(studentAnswer.equalsIgnoreCase(testBean.getCorrectAnswer())){
                      float score= testBean.getScore();
                      score++;
                     testBean.setScore(score);
                   }
                }
                //�����ȡ��һ��Ŀ��
                tihao++;
                testBean.setNumber(tihao); //���
                rs.absolute(index); //�����ȡ��Ŀ
                testBean.setQuestions(rs.getString(1));//��Ŀ����
                testBean.setChoiceA(rs.getString(2));  //��Ŀ��ѡ��a
                testBean.setChoiceB(rs.getString(3));  //��Ŀ��ѡ��b
                testBean.setChoiceC(rs.getString(4));  //��Ŀ��ѡ��c
                testBean.setChoiceD(rs.getString(5));  //��Ŀ��ѡ��d
                testBean.setImage(rs.getString(6));  //��Ŀ��ʾ��ͼ����
                testBean.setCorrectAnswer(rs.getString(7).trim());//��Ŀ�Ĵ�
                testBean.setMess("�����ǵ�"+tihao+"��");
                con.close(); 
            }
            else {
                testBean.setMess("�����������������鿴����");
                String studentAnswer=testBean.getAnswer(); //�ж����һ��
                if(studentAnswer!=null&&studentAnswer.length()>=1) {
                   if(studentAnswer.equalsIgnoreCase(testBean.getCorrectAnswer())){
                      float score= testBean.getScore();
                      score++;
                     testBean.setScore(score);
                   }
                }
                testBean.setAnswer(null);
                testBean.setNumber(0);
                testBean.setQuestions(null);
                testBean.setChoiceA(null);  
                testBean.setChoiceB(null);  
                testBean.setChoiceC(null);  
                testBean.setChoiceD(null);  
                testBean.setImage(null); 
            }
          }
          else {
             testBean.setMess("û�г鵽��Ŀ");
          }
          response.sendRedirect("example7_10_examination.jsp");
     }
     catch(SQLException e){
          notify(request,response,e.toString());
     }  
   }
   public  void  doGet(HttpServletRequest request,HttpServletResponse response)
           throws ServletException,IOException{
       doPost(request,response);
   }
   public void notify(HttpServletRequest request,HttpServletResponse response,
                      String backNews) {
        response.setContentType("text/html;charset=GB2312");
        try {
         PrintWriter out=response.getWriter();
         out.println("<html><body>");
         out.println("<h2>"+backNews+"</h2>") ;
         out.println("����");
         out.println("<a href =example7_10.jsp>����</a>");
         out.println("</body></html>");
        }
        catch(IOException exp){} 
    }
}

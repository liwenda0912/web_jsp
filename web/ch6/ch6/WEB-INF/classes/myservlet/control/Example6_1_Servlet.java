package myservlet.control;
import mybean.data.Example6_1_Bean;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Example6_1_Servlet extends HttpServlet{
   public void init(ServletConfig config) throws ServletException{
      super.init(config);
   }
   public void doPost(HttpServletRequest request,HttpServletResponse response)
                        throws ServletException,IOException{
      Example6_1_Bean seriesData=new Example6_1_Bean();  //����Javabean����
      request.setAttribute("seriesData",seriesData);//��seriesData�浽request������
      double a=Double.parseDouble(request.getParameter("firstItem);
      double d=Double.parseDouble(request.getParameter("var"));
      int n=Integer.parseInt(request.getParameter("number"));
      seriesData.setFirstItem(a);      //�����ݴ洢������ģ��seriesData�� 
      seriesData.setVar(d);           
      seriesData.setNumber(n);
      double sum=0,item=a;
      int i=1;
      seriesData.setName("�Ȳ����еĹ���");
      while(i<=n){                     //����Ȳ����еĺ�
         sum=sum+item;
         i++;
         item=item+d;  
      }
      seriesData.setSum(sum);
      //����example6_1_show.jsp��ʾseriesData�е�����:
      RequestDispatcher dispatcher=
      request.getRequestDispatcher("example6_1_show.jsp");
      dispatcher.forward(request,response);  
   } 
   public  void  doGet(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException{
      Example6_1_Bean seriesData=new Example6_1_Bean(); 
      request.setAttribute("seriesData",seriesData);
      double a=Double.parseDouble(request.getParameter("firstItem"));
      double d=Double.parseDouble(request.getParameter("var"));
      int n=Integer.parseInt(request.getParameter("number"));
      seriesData.setFirstItem(a);            
      seriesData.setVar(d);           
      seriesData.setNumber(n);
      double sum=0,item=a;
      int i=1;
      seriesData.setName("�ȱ����еĹ���");
      while(i<=n){                     //����ȱ����еĺ�
          sum=sum+item;
          i++;
          item=item*d;  
      }
      seriesData.setSum(sum);
      RequestDispatcher dispatcher=
      request.getRequestDispatcher("example6_1_show.jsp");
      dispatcher.forward(request,response);  
    }
}

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet(name = "Example5_2_Servlet",urlPatterns={"/computeBill"},loadOnStartup = 1)
public class Example5_2_Servlet extends HttpServlet {
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
    }
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body bgcolor=yellow>");
        String var4 = request.getParameter("billMess" );
        if (var4 != null && var4.length() != 0) {
            String[] var5 = var4.split("[^0123456789.]+");
            double var6 = 0.0D;

            try {
                for (int i = 0; i < var5.length; ++i) {
                    if (var5[i].length() >= 1) {
                        var6 += Double.parseDouble(var5[i]);
                    }
                }
            } catch (NumberFormatException e) {
                out.print(" " + e);
            }

            out.print("\"" + var4 + "\"<br>的消费额:" + var6 + "圆");
            out.println("</body></html>");
        }
    }
}

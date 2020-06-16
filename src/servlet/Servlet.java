package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import mybean.data.price;

@WebServlet(name = "Servlet",urlPatterns={"/phone"},loadOnStartup = 1)
public class Servlet extends HttpServlet {
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        price p=new price();
        int money = Integer.parseInt(request.getParameter("price"));
        String  number= request.getParameter("num");
        String  phonename= request.getParameter("phonename");
        p.setPrice(money);
        p.setPhonename(phonename);
        p.setNum(number);
        RequestDispatcher dispatcher =request.getRequestDispatcher("show.jsp");
        dispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}

package iti.jets.controller.servlets;

import iti.jets.dao.UserDao;
import iti.jets.util.ConnectionInstance;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.persistence.*;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONObject;

@WebServlet("/check_unique")
public class check_unique extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String username = request.getParameter("username");
        String email = request.getParameter("email");
        System.out.println("hello user");
        System.out.println("username "+username);
        System.out.println("email "+email);


        PrintWriter out = response.getWriter();

        ConnectionInstance connectionInstance=new ConnectionInstance((EntityManagerFactory) getServletContext().getAttribute("emf"));
        UserDao userDao=new UserDao(connectionInstance.getEntityManager());
        connectionInstance.openEntityManager();

        if(username!=null) {
            boolean result = userDao.checkUserName(username);
            if(result){
                out.print("user_found");
            }
            else{
                out.print("user_notfound");
            }
        }
        if(email!=null) {
            boolean result = userDao.checkEmail(email);
            if(result){
                out.print("email_found");
            }
            else{
                out.print("email_notfound");
            }
        }

        connectionInstance.closeEntityManager();
        out.flush();
    }

}



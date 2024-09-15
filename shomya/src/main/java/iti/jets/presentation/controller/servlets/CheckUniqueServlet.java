package iti.jets.presentation.controller.servlets;

import iti.jets.business.service.AuthService;
import iti.jets.persistence.dao.UserDao;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.persistence.*;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/checkunique")
public class CheckUniqueServlet extends HttpServlet {

 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         PrintWriter out = response.getWriter();
         ConnectionInstance connectionInstance = (ConnectionInstance) request.getSession().getAttribute("userConnection");
         try{
            String username = request.getParameter("username");
            String email = request.getParameter("email");
             if(username!=null) {
                 boolean result = AuthService.isUserNameFound(username,connectionInstance);
                 if(result){
                     out.print("user_found");
                 }
                 else{
                     out.print("user_notfound");
                 }
             }
             if(email!=null) {
                 boolean result = AuthService.isUserEmailFound(email,connectionInstance);
                 if(result){
                     out.print("email_found");
                 }
                 else{
                     out.print("email_notfound");
                 }
             }
         }
        catch(Exception e){
          e.printStackTrace();
          out.println("Error");
        }
        out.flush();
    }

}



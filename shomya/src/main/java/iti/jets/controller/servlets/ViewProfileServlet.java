package iti.jets.controller.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import iti.jets.dao.UserDao;
import iti.jets.model.Customer;
import iti.jets.service.helper.EnumHelper;
import iti.jets.util.ConnectionInstance;
import iti.jets.util.Factory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/ViewProfileServlet")
public class ViewProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/resources/jsp/ViewProfile.jsp").forward(request, response);
    }
}

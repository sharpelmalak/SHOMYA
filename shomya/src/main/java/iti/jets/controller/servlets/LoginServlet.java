package iti.jets.controller.servlets;

import iti.jets.dao.UserDao;
import iti.jets.model.Admin;
import iti.jets.model.CartItem;
import iti.jets.model.Customer;
import iti.jets.model.User;
import iti.jets.service.AuthService;
import iti.jets.service.CartService;
import iti.jets.service.helper.EnumHelper;
import iti.jets.util.ConnectionInstance;
import iti.jets.util.Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        // case user already loged in
        if (session != null && session.getAttribute("user") != null) {
            resp.sendRedirect("/shomya");
        }
        else req.getRequestDispatcher("/resources/login.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // get connection instance for that user

        ConnectionInstance connectionInstance = new ConnectionInstance((EntityManagerFactory) getServletContext().getAttribute("emf"));
        EntityManager entityManager = connectionInstance.getEntityManager();
        connectionInstance.openEntityManager();

        ///
        AuthService authService = new AuthService();
        authService.authUser(username, password,connectionInstance);
        if (authService.getUser() != null)
        {
            HttpSession session = req.getSession(true);
            if(session != null)
            {
                User user = authService.getUser();
                session.setAttribute("user", user);
                session.setAttribute("userRole", authService.getRole());
                session.setAttribute("userConnection", connectionInstance);
                if(authService.getRole() == EnumHelper.getCustomerRole())
                {
                    // load cart or create one
                    List<CartItem> cart = CartService.loadCart(user.getId(), connectionInstance.getEntityManager());
                    // delete cart from db
                    CartService.resetCart(user.getId(), connectionInstance.getEntityManager());
                    session.setAttribute("cart", cart);
                }
                connectionInstance.closeEntityManager();
                resp.sendRedirect("/shomya");
            }
        }
        else
        {
            // invalid credentials // error no user // error msg
            req.getRequestDispatcher("/resources/login.html").forward(req, resp);
        }
    }
}

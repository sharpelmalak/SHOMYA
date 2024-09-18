package iti.jets.presentation.controller.servlets;

import iti.jets.persistence.model.CartItem;
import iti.jets.persistence.model.User;
import iti.jets.business.service.AuthService;
import iti.jets.business.service.CartService;
import iti.jets.business.service.helper.EnumHelper;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



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
        HttpSession session = req.getSession();
        ConnectionInstance connectionInstance = (ConnectionInstance)session.getAttribute("userConnection");
        ///
        AuthService authService = new AuthService();
        authService.authUser(username, password,connectionInstance);
        if (authService.getUser() != null)
        {
            if(session != null)
            {
                User user = authService.getUser();
                session.setAttribute("user", user);
                session.setAttribute("userRole", authService.getRole());

                if(authService.getRole() == EnumHelper.getCustomerRole())
                {
                    // load cart or create one
                    List<CartItem> cart = CartService.loadCart(user.getId(), connectionInstance);
                    // delete cart from db
                    CartService.resetCart(user.getId(), connectionInstance);
                    System.out.println("after login my cart size is " + cart.size());
                    for(CartItem cartItem : cart){
                        System.out.println(cartItem);
                    }
                    session.setAttribute("cart", cart);
                }

                resp.sendRedirect("/shomya");
            }
        }
        else
        {
            // invalid credentials // error no user // error msg
            resp.sendRedirect("/shomya/app/login?error=invalidCredentials");
        }
    }
}

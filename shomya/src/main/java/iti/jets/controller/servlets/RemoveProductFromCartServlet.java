package iti.jets.controller.servlets;

import iti.jets.model.CartItem;
import iti.jets.service.CartService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/removeProductfromCart")
public class RemoveProductFromCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("id");

        // Assuming you have a Cart object in the session
        HttpSession session = request.getSession(false);
        if (session != null) {
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            CartService.removeFromCart(cart,Integer.parseInt(productId));
            response.getWriter().write("success");
        }
        else
        {
            response.getWriter().write("failure");
        }

    }
}


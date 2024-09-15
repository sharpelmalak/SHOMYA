package iti.jets.presentation.controller.servlets;

import iti.jets.persistence.model.CartItem;
import iti.jets.persistence.model.Customer;
import iti.jets.business.service.CartService;
import iti.jets.business.service.helper.EnumHelper;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet("/addtocart")
public class AddProductToCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        if (session != null) {
            if(session.getAttribute("userRole")== EnumHelper.getCustomerRole())
            {
                ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
                EntityManager em = connectionInstance.getEntityManager();
                try{
                    int productId = Integer.parseInt(req.getParameter("productId"));
                    int quantity = Integer.parseInt(req.getParameter("quantity"));
                    List<CartItem> cart = (List<CartItem>)session.getAttribute("cart");
                    boolean isProduct = CartService.checkProductInCart(cart, productId, quantity);
                    if(!isProduct)
                    {
                        CartItem cartItem = CartService.addProductToCart((Customer) session.getAttribute("user"),productId,quantity,connectionInstance);
                        if (cartItem != null) {
                            cart.add(cartItem);
                            out.print("done");
                        }
                        else out.print("error");

                    }
                    else{
                        out.print("view");
                    }
                }catch (Exception e)
                {
                    out.print("error");
                }

            }
            else resp.sendRedirect("/shomya");
        }
    }

}

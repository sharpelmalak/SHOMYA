package iti.jets.controller.servlets;

import iti.jets.model.CartItem;
import iti.jets.model.Customer;
import iti.jets.service.CartService;
import iti.jets.service.helper.EnumHelper;
import iti.jets.util.ConnectionInstance;
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

@WebServlet("/addtocart")
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
                    boolean isProduct = CartService.chekProductInCart(cart, productId, quantity);
                    if(!isProduct)
                    {
                        connectionInstance.openEntityManager();
                        CartItem cartItem = CartService.addProductToCart((Customer) session.getAttribute("user"),productId,quantity,em);
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
                }finally {
                    connectionInstance.closeEntityManager();
                }

            }
            else resp.sendRedirect("/shomya");
        }
    }

}

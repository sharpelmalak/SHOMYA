package iti.jets.controller.servlets;

import iti.jets.dao.CategoryDao;
import iti.jets.model.CartItem;
import iti.jets.model.Customer;
import iti.jets.service.AddToCart;
import iti.jets.service.helper.EnumHelper;
import iti.jets.util.ConnectionInstance;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                    boolean isProduct = AddToCart.chekProductInCart(cart, productId, quantity);
                    if(!isProduct)
                    {
                        connectionInstance.openEntityManager();
                        CartItem cartItem = AddToCart.addProductToCart((Customer) session.getAttribute("user"),productId,quantity,em);
                        if (cartItem != null) {
                            cart.add(cartItem);
                            System.out.println(cart.size());
                            out.print("done");
                        }
                        else out.print("error");

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Get product details from request
        String productId = request.getParameter("productId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Get cart from session (or create new one if null)
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }

        // Add or update product in the cart
        cart.put(productId, cart.getOrDefault(productId, 0) + quantity);

        // Store the updated cart back in the session
        session.setAttribute("cart", cart);

        // Return success response as JSON
        JsonObject jsonResponse = Json.createObjectBuilder()
                .add("status", "success")
                .build();

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
    }
}

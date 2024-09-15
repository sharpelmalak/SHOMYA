package iti.jets.presentation.controller.servlets;


import iti.jets.business.service.ProductService;
import iti.jets.persistence.dao.ProductDao;
import iti.jets.persistence.model.Product;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;


public class ProductImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        try{
            int productId = Integer.parseInt(request.getParameter("productId"));
            Product product = ProductService.getProduct(connectionInstance, productId);
            if (product != null && product.getImage() != null) {
                // Set the response content type to the appropriate image type (e.g., JPEG, PNG)
                response.setContentType("image/jpeg"); // Assuming the image is JPEG
                // Write the image data to the response
                response.getOutputStream().write(product.getImage());
                response.getOutputStream().flush();
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND); // Send 404 if no image is found
            }
        }
        catch (Exception e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}


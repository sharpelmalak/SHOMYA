package iti.jets.controller.servlets;


import iti.jets.dao.ProductDao;
import iti.jets.model.Product;
import iti.jets.util.ConnectionInstance;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/productImage")
public class ProductImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the product ID from the request
        String productIdStr = request.getParameter("productId");
        if (productIdStr != null) {
            int productId = Integer.parseInt(productIdStr);

            // Retrieve the product from the database
            HttpSession session = request.getSession(false);
            ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
            ProductDao productDao = new ProductDao(connectionInstance.getEntityManager());
            Product product = productDao.findById(productId);

            if (product != null && product.getImage() != null) {
                // Set the response content type to the appropriate image type (e.g., JPEG, PNG)
                response.setContentType("image/jpeg"); // Assuming the image is JPEG
                // Write the image data to the response
                response.getOutputStream().write(product.getImage());
                response.getOutputStream().flush();
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND); // Send 404 if no image is found
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST); // Send 400 if no product ID is provided
        }
    }
}


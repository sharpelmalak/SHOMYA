package iti.jets.presentation.controller.servlets;


import iti.jets.persistence.dao.CategoryDao;
import iti.jets.persistence.model.Category;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

//@WebServlet("/categoryImage")
public class CategoryImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the product ID from the request
        String id = request.getParameter("catId");
        if (id != null) {
            int categoryId = Integer.parseInt(id);

            // Retrieve the product from the database
            HttpSession session = request.getSession(false);
            ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
            CategoryDao categoryDao = new CategoryDao(connectionInstance.getEntityManager());
            Category category = categoryDao.findById(categoryId);
            if (category != null && category.getImage() != null) {

                System.out.println("image found-----------");
                // Set the response content type to the appropriate image type (e.g., JPEG, PNG)
                response.setContentType("image/jpeg"); // Assuming the image is JPEG
                // Write the image data to the response
                response.getOutputStream().write(category.getImage());
                response.getOutputStream().flush();
            } else {
                System.out.println("image not found-----------");
                response.sendError(HttpServletResponse.SC_NOT_FOUND); // Send 404 if no image is found
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST); // Send 400 if no product ID is provided
        }
    }
}


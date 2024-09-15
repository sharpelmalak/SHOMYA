package iti.jets.presentation.controller.servlets;


import iti.jets.business.service.CategoryService;
import iti.jets.persistence.dao.CategoryDao;
import iti.jets.persistence.model.Category;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;


public class CategoryImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        try{
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            Category category = CategoryService.getCategory(connectionInstance, categoryId);
            if (category != null && category.getImage() != null) {
                // Set the response content type to the appropriate image type (e.g., JPEG, PNG)
                response.setContentType("image/jpeg"); // Assuming the image is JPEG
                // Write the image data to the response
                response.getOutputStream().write(category.getImage());
                response.getOutputStream().flush();
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND); // Send 404 if no image is found
            }
        }
        catch(Exception e){
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);

        }

    }
}


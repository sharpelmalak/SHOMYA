package iti.jets.presentation.controller.servlets;


import iti.jets.business.service.CategoryService;
import iti.jets.business.service.helper.EnumHelper;
import iti.jets.persistence.model.Category;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/app/updatecategory")
@MultipartConfig
public class UpdateCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session.getAttribute("userRole") == EnumHelper.getAdminRole()) {
            try {
                ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
                int id = Integer.parseInt(req.getParameter("catId"));
                req.setAttribute("category", CategoryService.getCategory(connectionInstance, id));
                req.getRequestDispatcher("/resources/jsp/updateCategory.jsp").forward(req, resp);

            }catch (Exception e) {
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        else {
            resp.sendRedirect("/shomya");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session.getAttribute("userRole") == EnumHelper.getAdminRole()) {
            try {
                ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
                int id = Integer.parseInt(req.getParameter("catId"));
                String catName = req.getParameter("categoryName");
                Part filePart = req.getPart("categoryImage");
                byte[] imageData = null;
                // if new img added
                if (filePart != null && filePart.getSize() > 0) {
                    InputStream inputStream = filePart.getInputStream();
                    imageData = inputStream.readAllBytes();
                }
                Category category = CategoryService.updateCategory(connectionInstance, id, catName, imageData);
                if (category != null) {
                    req.setAttribute("category", category);
                    req.getRequestDispatcher("/resources/jsp/updateCategory.jsp").forward(req, resp);
                }
                else {
                    resp.sendRedirect("/shomya");
                }

            }catch (Exception e) {
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        else {
            resp.sendRedirect("/shomya");
        }
    }
}

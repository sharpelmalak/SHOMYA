package iti.jets.presentation.controller.servlets;

import iti.jets.business.service.CartService;
import iti.jets.business.service.CategoryService;
import iti.jets.persistence.dao.CategoryDao;
import iti.jets.persistence.model.Category;
import iti.jets.business.service.helper.EnumHelper;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;

@WebServlet(value = "/app/addCategory")
@MultipartConfig
public class AddCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/shomya/app/categories");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session.getAttribute("userRole") != EnumHelper.getAdminRole())
        {
            resp.sendRedirect("/shomya");
        }
        else
        {
            String name = req.getParameter("categoryName");
            Part filePart = req.getPart("categoryImage");
            byte[] imageData = null;

            try{
                InputStream inputStream = filePart.getInputStream();
                imageData = inputStream.readAllBytes();
                ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
                boolean isAdded = CategoryService.addCategory(name,imageData,connectionInstance);
                // TO DO Handle Exception
                if (isAdded){
                    resp.sendRedirect("/shomya/app/categories");
                }
                else
                {
                    resp.sendRedirect("/shomya/app/categories");
                }

            }catch (Exception e)
            {
                e.printStackTrace();
                resp.sendRedirect("/shomya/app/categories");
            }


        }


    }
}

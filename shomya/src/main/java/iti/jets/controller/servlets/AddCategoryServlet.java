package iti.jets.controller.servlets;

import iti.jets.dao.CategoryDao;
import iti.jets.model.Category;
import iti.jets.util.ConnectionInstance;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@WebServlet(value = "/addCategory")
@MultipartConfig
public class AddCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            if(session.getAttribute("user")!=null)
            {
                resp.sendRedirect("/categories");
            }
            else resp.sendRedirect("/shomya/login");
        }
        else  resp.sendRedirect("/shomya/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session != null)
        {
            if(session.getAttribute("user") == null)
                 resp.sendRedirect("/login");
        }
        String name = req.getParameter("categoryName");
        Part filePart = req.getPart("categoryImage");
        System.out.println(filePart.getSubmittedFileName());
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        // Save the file to the specified directory
        String realPath = getServletContext().getRealPath("/resources/img/");
        File imageFolder = new File(realPath);
        if (!imageFolder.exists()) {
            imageFolder.mkdirs();  // Create directory if it doesn't exist
        }
        File file = new File(realPath + fileName);
        Files.copy(filePart.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);

        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        Category category = new Category(name,fileName);
        CategoryDao categoryDao = new CategoryDao(connectionInstance.getEntityManager());
        connectionInstance.openEntityManager();
        categoryDao.save(category);
        connectionInstance.closeEntityManager();
        resp.sendRedirect("/shomya/categories");

    }
}

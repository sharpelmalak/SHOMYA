package iti.jets.presentation.controller.servlets;

import iti.jets.persistence.dao.CategoryDao;
import iti.jets.persistence.dao.ProductDao;
import iti.jets.persistence.model.Admin;
import iti.jets.persistence.model.Category;
import iti.jets.persistence.model.Product;
import iti.jets.business.service.helper.EnumHelper;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;


@MultipartConfig
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession(false);
        if(session.getAttribute("userRole")== EnumHelper.getAdminRole())
        {
            ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
            CategoryDao categoryDao = new CategoryDao(connectionInstance.getEntityManager());
            List<Category> categoryList = categoryDao.findAll();
            req.setAttribute("categoryList", categoryList);
            req.getRequestDispatcher("/resources/jsp/addProducts.jsp").forward(req,resp);

        }
        else resp.sendRedirect("/shomya");
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session= req.getSession(false);
        Enumeration<String> parameterNames = req.getParameterNames();

        // Loop through parameter names
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            // Print parameter name and its value(s)
            System.out.println("Parameter Name: " + paramName);

        }
        if(session.getAttribute("userRole")== EnumHelper.getAdminRole())
        {
            System.out.println("Start Post");
            String name = req.getParameter("pname");
            System.out.println("Start Post name " + name);
            int catId = Integer.parseInt(req.getParameter("categoryId"));
            System.out.println("Start Post name " + catId);
            float price = Float.parseFloat(req.getParameter("pprice"));
            System.out.println("Start Post name " + price);
            int quantity = Integer.parseInt(req.getParameter("pquantity"));
            System.out.println("Start Post name " + quantity);
            String description = req.getParameter("pdesc");
            System.out.println("hereeeeeeeeeeeeeeeeeeeeeeeeeeee");
            Part filePart = req.getPart("pimage");
            // Read image data from the Part
            byte[] imageData = null;
            try (InputStream inputStream = filePart.getInputStream()) {
                imageData = inputStream.readAllBytes();
            }
            ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");

            CategoryDao categoryDao= new CategoryDao(connectionInstance.getEntityManager());
            Category category =categoryDao.findById(catId);
            ProductDao productDao=new ProductDao(connectionInstance.getEntityManager());
            Product product = new Product((Admin)session.getAttribute("user"),category,name, price, quantity, description, imageData);
            productDao.save(product);
            resp.sendRedirect("/shomya/app/products");


        }

        else
        {
            resp.sendRedirect("/shomya");
        }
    }


//    private void EditProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException
//    {
//        int productId = Integer.parseInt(req.getParameter("productId"));
//        String newName = req.getParameter("name");
//        float newPrice = Float.parseFloat(req.getParameter("price"));
//        int newQuantity = Integer.parseInt(req.getParameter("quantity"));
//        String newDescription = req.getParameter("description");
//        Product product = productDao.findById(productId);
//        if (product != null)
//        {
//            product.setName(newName);
//            product.setPrice(newPrice);
//            product.setQuantity(newQuantity);
//            product.setDescription(newDescription);
//            productDao.update(product);
//
//            resp.getWriter().write("Product modified successfully!");
//        }
//        else
//        {
//            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
//        }
//    }
//
//    // Method to remove product
//    private void RemoveProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        int productId = Integer.parseInt(req.getParameter("productId"));
//        productDao.deleteById(productId);
//        resp.getWriter().write("Product removed successfully!");
//    }
}



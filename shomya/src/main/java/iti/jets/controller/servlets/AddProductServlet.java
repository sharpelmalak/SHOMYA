package iti.jets.controller.servlets;

import iti.jets.dao.CategoryDao;
import iti.jets.dao.ProductDao;
import iti.jets.model.Admin;
import iti.jets.model.Category;
import iti.jets.model.Product;
import iti.jets.service.helper.EnumHelper;
import iti.jets.util.ConnectionInstance;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@WebServlet(value = "/addproduct")
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
            connectionInstance.openEntityManager();
            List<Category> categoryList = categoryDao.findAll();
            connectionInstance.closeEntityManager();
            req.getRequestDispatcher("/resources/jsp/addProducts.jsp").forward(req,resp);
            req.setAttribute("categoryList", categoryList);
        }
        else resp.sendRedirect("/shomya");
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session= req.getSession(false);
        if(session.getAttribute("userRole")== EnumHelper.getAdminRole())
        {
            String name = req.getParameter("pname");
            int catId = Integer.parseInt(req.getParameter("categoryId"));
            float price = Float.parseFloat(req.getParameter("pprice"));
            int quantity = Integer.parseInt(req.getParameter("pquantity"));
            String description = req.getParameter("pdesc");
            Part filePart = req.getPart("pimage");
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

            CategoryDao categoryDao= new CategoryDao(connectionInstance.getEntityManager());
            connectionInstance.openEntityManager();
            Category category =categoryDao.findById(catId);
            ProductDao productDao=new ProductDao(connectionInstance.getEntityManager());
            Product product = new Product((Admin)session.getAttribute("user"),category,name, price, quantity, description, fileName);
            productDao.save(product);
            connectionInstance.closeEntityManager();
            resp.sendRedirect("/shomya/products");


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



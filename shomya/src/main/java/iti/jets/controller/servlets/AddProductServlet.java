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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@WebServlet(value = "/addproduct")
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession(false);
        if (session != null) {
            if(session.getAttribute("user")!=null&& session.getAttribute("userRole")== EnumHelper.getAdminRole())
            {
                ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
                CategoryDao categoryDao = new CategoryDao(connectionInstance.getEntityManager());
                connectionInstance.openEntityManager();
                List<Category> categoryList = categoryDao.findAll();
                connectionInstance.closeEntityManager();
                req.setAttribute("categoryList", categoryList);
                req.getRequestDispatcher("/resources/jsp/addProducts.jsp").forward(req,resp);

            }
            else resp.sendRedirect("/shomya/login");
        }
        else  resp.sendRedirect("/shomya/login");
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session= req.getSession(false);
        if (session !=null)
        {
            if(session.getAttribute("user")!=null && session.getAttribute("userRole")== EnumHelper.getAdminRole())
            {
                String categoryId = req.getParameter("categoryId");
                System.out.println("Selected Category ID: " + categoryId);
                int catId = Integer.parseInt(categoryId);
                String name = req.getParameter("pname");
                float price = Float.parseFloat(req.getParameter("pprice"));
                int quantity = Integer.parseInt(req.getParameter("pquantity"));
                String description = req.getParameter("pdesc");
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

                CategoryDao categoryDao= new CategoryDao(connectionInstance.getEntityManager());
                connectionInstance.openEntityManager();
                Category category =categoryDao.findById(catId);
                ProductDao productDao=new ProductDao(connectionInstance.getEntityManager());
                Product product = new Product((Admin)session.getAttribute("user"),category,name, price, quantity, description, fileName);
                productDao.save(product);
                resp.getWriter().write("Product added successfully!");
                connectionInstance.closeEntityManager();


            }

            else
            {
                resp.sendRedirect("/shomya/login");
            }
        }

        else
        {
            resp.sendRedirect("/shomya/login");
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



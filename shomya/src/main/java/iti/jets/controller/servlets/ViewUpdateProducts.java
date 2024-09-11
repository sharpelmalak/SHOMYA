package iti.jets.controller.servlets;

import iti.jets.dao.CategoryDao;
import iti.jets.dao.ProductDao;
import iti.jets.model.Admin;
import iti.jets.model.Category;
import iti.jets.model.Product;
import iti.jets.service.helper.EnumHelper;
import iti.jets.util.ConnectionInstance;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@WebServlet("/viewproduct")
@MultipartConfig
public class ViewUpdateProducts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            int productId = Integer.parseInt(req.getParameter("productId"));
            ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
            ProductDao productDao = new ProductDao(connectionInstance.getEntityManager());
            connectionInstance.openEntityManager();
            CategoryDao categoryDao = new CategoryDao(connectionInstance.getEntityManager());
            List<Category> categoryList = categoryDao.findAll();
            req.setAttribute("categoryList", categoryList);
            Product product = productDao.findById(productId);
            String name = product.getCategory().getName();
            connectionInstance.closeEntityManager();
            if (product != null) {

                req.setAttribute("product", product);
                if (session.getAttribute("userRole") == EnumHelper.getAdminRole()) {
                    req.getRequestDispatcher("/resources/jsp/updateProduct.jsp").forward(req, resp);
                }
                else
                {
                    req.getRequestDispatcher("/resources/jsp/viewProduct.jsp").forward(req, resp);
                }
            }
            else {
                System.out.println("No product found");
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session.getAttribute("userRole") == EnumHelper.getAdminRole()) {


            // read params
            int id = Integer.parseInt(req.getParameter("productID"));
            String name = req.getParameter("pname");
            int catId = Integer.parseInt(req.getParameter("categoryId"));
            float price = Float.parseFloat(req.getParameter("pprice"));
            int quantity = Integer.parseInt(req.getParameter("pquantity"));
            String description = req.getParameter("pdesc");
            Part filePart = req.getPart("pimage");

            // open connection
            ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
            CategoryDao categoryDao = new CategoryDao(connectionInstance.getEntityManager());
            ProductDao productDao = new ProductDao(connectionInstance.getEntityManager());
            connectionInstance.openEntityManager();

            // get new category and product
            Category category = categoryDao.findById(catId);
            Product product = productDao.findById(id);

           // set new data
            product.setName(name);
            product.setCategory(category);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setDescription(description);
            // keep old name
            String fileName = product.getImage();
            // if new img added
            if (filePart != null && filePart.getSize() > 0) {


                // Save the file to the specified directory
                String realPath = getServletContext().getRealPath("/resources/img/");
                File imageFolder = new File(realPath);
                if (!imageFolder.exists()) {
                    imageFolder.mkdirs();  // Create directory if it doesn't exist
                }
                try{
                    Files.delete(Paths.get(realPath + fileName));
                }catch (Exception e)
                {

                }
                // delete old img

                fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                File file = new File(realPath + fileName);
                // save new img in path
                Files.copy(filePart.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                // set new image
                product.setImage(fileName);
            }
            // update product
            product = productDao.update(product);

            //close connection
            connectionInstance.closeEntityManager();
            if (product != null) {
                resp.sendRedirect("/shomya/viewproduct?productId=" + product.getId());
            }
            else resp.sendRedirect("/shomya/");


        }

    }
}

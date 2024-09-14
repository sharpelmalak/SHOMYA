package iti.jets.presentation.controller.servlets;

import iti.jets.persistence.dao.CategoryDao;
import iti.jets.persistence.dao.ProductDao;
import iti.jets.persistence.model.Category;
import iti.jets.persistence.model.Product;
import iti.jets.business.service.helper.EnumHelper;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//@WebServlet("/viewproduct")
@MultipartConfig
public class ViewUpdateProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            int productId = Integer.parseInt(req.getParameter("productId"));
            ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
            ProductDao productDao = new ProductDao(connectionInstance.getEntityManager());
            CategoryDao categoryDao = new CategoryDao(connectionInstance.getEntityManager());
            List<Category> categoryList = categoryDao.findAll();
            req.setAttribute("categoryList", categoryList);
            Product product = productDao.findById(productId);
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

            // if new img added
            if (filePart != null && filePart.getSize() > 0) {

                byte[] imageData = null;
                try (InputStream inputStream = filePart.getInputStream()) {
                    imageData = inputStream.readAllBytes();
                }

                product.setImage(imageData);
            }
            // update product
            product = productDao.update(product);

            if (product != null) {
                resp.sendRedirect("/shomya/app/viewproduct?productId=" + product.getId());
            }
            else resp.sendRedirect("/shomya/app/");


        }

    }
}

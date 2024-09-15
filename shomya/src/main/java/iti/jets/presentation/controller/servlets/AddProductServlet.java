package iti.jets.presentation.controller.servlets;

import iti.jets.business.service.CategoryService;
import iti.jets.business.service.ProductService;
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
import java.util.List;

@WebServlet(value = "/app/addproduct")
@MultipartConfig
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession(false);
        if(session.getAttribute("userRole")== EnumHelper.getAdminRole())
        {
            ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
            req.setAttribute("categoryList", CategoryService.getCategories(connectionInstance));
            req.getRequestDispatcher("/resources/jsp/addProducts.jsp").forward(req,resp);

        }
        else resp.sendRedirect("/shomya");
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session= req.getSession(false);
        if(session.getAttribute("userRole")== EnumHelper.getAdminRole())
        {
            ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
            try{
                String name = req.getParameter("pname");
                int catId = Integer.parseInt(req.getParameter("categoryId"));
                float price = Float.parseFloat(req.getParameter("pprice"));
                int quantity = Integer.parseInt(req.getParameter("pquantity"));
                String description = req.getParameter("pdesc");
                Part filePart = req.getPart("pimage");
                // Read image data from the Part
                byte[] imageData = null;
                InputStream inputStream = filePart.getInputStream();
                imageData = inputStream.readAllBytes();
                boolean isAdded = ProductService.addProduct(connectionInstance,(Admin)session.getAttribute("user"),name,catId,price,quantity,description,imageData);
                if(isAdded)
                {
                    resp.sendRedirect("/shomya/app/products");
                }
                else {
                    resp.sendRedirect("/shomya/app/products");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                resp.sendRedirect("/shomya/app/products");
            }





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



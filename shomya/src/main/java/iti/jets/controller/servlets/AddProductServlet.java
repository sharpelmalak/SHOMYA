package iti.jets.controller.servlets;

import iti.jets.dao.ProductDao;
import iti.jets.model.Admin;
import iti.jets.model.Category;
import iti.jets.model.Product;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AddProductServlet extends HttpServlet {

ProductDao productDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session= req.getSession();
        Category cat = null;
        String name = req.getParameter("name");
        float price = Float.parseFloat(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String description = req.getParameter("description");
        Product product = new Product((Admin)session.getAttribute("user"),cat,name, price, quantity, description, image);
        productDao.save(product);
        resp.getWriter().write("Product added successfully!");

    }


    private void EditProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        int productId = Integer.parseInt(req.getParameter("productId"));
        String newName = req.getParameter("name");
        float newPrice = Float.parseFloat(req.getParameter("price"));
        int newQuantity = Integer.parseInt(req.getParameter("quantity"));
        String newDescription = req.getParameter("description");
        Product product = productDao.findById(productId);
        if (product != null)
        {
            product.setName(newName);
            product.setPrice(newPrice);
            product.setQuantity(newQuantity);
            product.setDescription(newDescription);
            productDao.update(product);

            resp.getWriter().write("Product modified successfully!");
        }
        else
        {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
        }
    }

    // Method to remove product
    private void RemoveProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        productDao.deleteById(productId);
        resp.getWriter().write("Product removed successfully!");
    }
}



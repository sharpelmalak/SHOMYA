package iti.jets.controller.servlets;

import iti.jets.dao.CategoryDao;
import iti.jets.dao.ProductDao;
import iti.jets.model.Category;
import iti.jets.model.Product;
import iti.jets.util.ConnectionInstance;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/products")
public class ProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            if (session.getAttribute("user") != null)
            {
                ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
                ProductDao productDao = new ProductDao(connectionInstance.getEntityManager());
                connectionInstance.openEntityManager();
                List<Product> productList = productDao.findAll();
                connectionInstance.closeEntityManager();
                req.setAttribute("productList", productList);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/resources/jsp/products.jsp");
                requestDispatcher.forward(req, resp);

            }
            else resp.sendRedirect("/shomya/login");

        }
        else {
            resp.sendRedirect("/shomya/login");
        }

    }

}

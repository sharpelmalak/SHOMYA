package iti.jets.presentation.controller.servlets;

import iti.jets.business.service.CategoryService;
import iti.jets.business.service.ProductService;
import iti.jets.persistence.dao.CategoryDao;
import iti.jets.persistence.dao.ProductDao;
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

@WebServlet("/app/viewproduct")
@MultipartConfig
public class ViewUpdateProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            try{
                int productId = Integer.parseInt(req.getParameter("productId"));
                ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
                req.setAttribute("categoryList", CategoryService.getCategories(connectionInstance));
                req.setAttribute("product", ProductService.getProduct(connectionInstance, productId));
                if (session.getAttribute("userRole") == EnumHelper.getAdminRole()) {
                    req.getRequestDispatcher("/resources/jsp/updateProduct.jsp").forward(req, resp);
                }
                else
                {
                    req.getRequestDispatcher("/resources/jsp/viewProduct.jsp").forward(req, resp);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                resp.sendRedirect("/shomya");
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session.getAttribute("userRole") == EnumHelper.getAdminRole()) {

            try{
                ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
                // read params
                int id = Integer.parseInt(req.getParameter("productID"));
                String name = req.getParameter("pname");
                int catId = Integer.parseInt(req.getParameter("categoryId"));
                float price = Float.parseFloat(req.getParameter("pprice"));
                int quantity = Integer.parseInt(req.getParameter("pquantity"));
                String description = req.getParameter("pdesc");
                Part filePart = req.getPart("pimage");
                byte[] imageData = null;
                // if new img added
                if (filePart != null && filePart.getSize() > 0) {
                    InputStream inputStream = filePart.getInputStream();
                    imageData = inputStream.readAllBytes();
                }
                Product product= ProductService.updateProduct(connectionInstance,id,name,catId,price,quantity,description,imageData);
                if(product!=null)
                {
                    resp.sendRedirect("/shomya/app/viewproduct?productId=" + product.getId());
                }
                else {
                    resp.sendRedirect("/shomya/");
                }

            }
            catch (Exception e) {
                resp.sendRedirect("/shomya/");
            }


        }

    }
}

package iti.jets.presentation.controller;

import iti.jets.presentation.controller.servlets.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/app/*",""})
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        if (path == null) {
            path = "/";
        }

        switch (path) {
            case "/shop":
                new ShopServlet().service(request, response);
                break;
            case "/viewcart":
                new ViewCartServlet().service(request, response);
                break;
            case "/products":
                new ProductsServlet().service(request, response);
                break;
            case "/addtocart":
                new AddProductToCartServlet().service(request, response);
                break;
            case "/calculateTotal":
                new CalculateTotalServlet().service(request, response);
                break;
            case "/categories":
                new CategoriesServlet().service(request, response);
                break;
            case "/checkCartAndCredit":
                new CheckCartAndCreditLimitServlet().service(request, response);
                break;
            case "/checkout":
                new CheckoutServlet().service(request, response);
                break;
            case "/checkunique":
                new CheckUniqueServlet().service(request, response);
                break;
            case "/view-interests":
                new CustomerInterests().service(request, response);
                break;
            case "/deletecategory":
                new DeleteCategoryServlet().service(request, response);
                break;
            case "/deleteproduct":
                new DeleteProductServlet().service(request, response);
                break;
            case "/login":
                new LoginServlet().service(request, response);
                break;
            case "/logout":
                new LogoutServlet().service(request, response);
                break;
            case "/customerOrder":
                new OrdersByCustomerIdServlet().service(request, response);
                break;
            case "/productImage":
                new ProductImageServlet().service(request, response);
                break;
            case "/registration":
                new RegisterationServlet().service(request, response);
                break;
            case "/removeProductfromCart":
                new RemoveProductFromCartServlet().service(request, response);
                break;
            case "/search-product":
                new SearchProductServlet().service(request, response);
                break;
            case "/customers":
                new ViewCustomersServlet().service(request, response);
                break;
            case "/updateprofile":
                new UpdateProfileServlet().service(request, response); // Use service() to delegate to the appropriate method
                break;
            case "/vieworder":
                new ViewOrderServlet().service(request, response); // Use service() to delegate to the appropriate method
                break;
            case "/viewprofile":
                new ViewProfileServlet().service(request, response);
                break;
            case "/categoryImage":
                new CategoryImageServlet().service(request, response);
                break;
            default:
                request.getRequestDispatcher("/resources/index.jsp").forward(request, response);
                break;
        }
    }
}

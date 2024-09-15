package iti.jets.presentation.filters;


import iti.jets.persistence.util.ConnectionInstance;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class MainFilter implements Filter {

    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // init filter
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        // detroyed
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        ConnectionInstance connectionInstance = (ConnectionInstance)httpRequest.getSession().getAttribute("userConnection");

        // Get the requested URI
        String requestURI = httpRequest.getRequestURI();
        System.out.println(requestURI);
        // Check if the URI should be excluded from the filter logic
        if (
                requestURI.equals(httpRequest.getContextPath() + "/") // Root path (home)
                        || requestURI.endsWith("registration")
                        || requestURI.endsWith("checkunique")
                        || requestURI.endsWith("login")
                        || requestURI.contains("/resources")
                           ) {
            // Allow access to home, register, and login without filtering
            filterChain.doFilter(servletRequest, servletResponse);
            if(connectionInstance != null)
                connectionInstance.closeEntityManager();
            // close any connection
        } else {
            // Apply filter logic for other pages
            // Example: check if user is logged in (you can customize this part)
            if (httpRequest.getSession().getAttribute("user") != null) {
                // User is logged in, continue to requested resource
                filterChain.doFilter(servletRequest, servletResponse);
                // close any connection
                if(connectionInstance != null)
                    connectionInstance.closeEntityManager();
            } else {
                // Redirect to login page if not logged in
                httpResponse.sendRedirect("/shomya/app/login");
            }
        }
    }
}

package iti.jets.controller.filters;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(value = "/*")
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

        // Get the requested URI
        String requestURI = httpRequest.getRequestURI();
        System.out.println(requestURI);
        // Check if the URI should be excluded from the filter logic
        if (
                requestURI.equals(httpRequest.getContextPath() + "/") // Root path (home)
                        || requestURI.endsWith("registration")
                        || requestURI.endsWith("login")
                        || requestURI.contains("/resources/")  // Exclude static files, assuming they are under /resources/
                           ) {
            // Allow access to home, register, and login without filtering
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // Apply filter logic for other pages
            // Example: check if user is logged in (you can customize this part)
            if (httpRequest.getSession().getAttribute("user") != null) {
                // User is logged in, continue to requested resource
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                // Redirect to login page if not logged in
                httpResponse.sendRedirect("/shomya/login");
            }
        }
    }
}

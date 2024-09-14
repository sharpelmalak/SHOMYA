//package iti.jets.controller.filters;
//
//import iti.jets.util.EntityManagerUtil;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.EntityManager;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//
//import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//@WebFilter(value = "/*")
//public class EntityManagerFilter implements Filter {
//    private Logger itsLogger = Logger.getLogger(getClass().getName());
//    private static volatile EntityManagerFactory theEntityManagerFactory = null;
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//        String requestURI = httpRequest.getRequestURI();
//        // Exclude /resources/* paths
//        if (requestURI.contains("/resources/")) {
//            // Skip the filter for /resources/
//            filterChain.doFilter(servletRequest, servletResponse);
//            return;
//        }
//        EntityManager em = null;
//        try {
//
//            if (theEntityManagerFactory != null) {
//                em = theEntityManagerFactory.createEntityManager();
//                EntityManagerUtil.ENTITY_MANAGERS.set(em);
//                itsLogger.info("EntityManager created and set for the current thread.");
//            } else {
//                itsLogger.warning("EntityManagerFactory is null. Cannot create EntityManager.");
//            }
//
//            filterChain.doFilter(servletRequest, servletResponse);
//
//        } finally {
//            // Clean up
//            EntityManagerUtil.ENTITY_MANAGERS.remove();
//            itsLogger.info("EntityManager removed from the current thread.");
//
//            if (em != null) {
//                try {
//                    em.close();
//                    itsLogger.info("EntityManager closed.");
//                } catch (Exception e) {
//                    itsLogger.log(Level.SEVERE, "Error while closing EntityManager", e);
//                }
//            }
//        }
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        destroy();
//        theEntityManagerFactory = (EntityManagerFactory) filterConfig.getServletContext().getAttribute("emf");
//        if (theEntityManagerFactory == null) {
//            itsLogger.warning("EntityManagerFactory not found in servlet context.");
//        } else {
//            itsLogger.info("EntityManagerFactory initialized.");
//        }
//    }
//
//    @Override
//    public void destroy() {
//        if (theEntityManagerFactory != null) {
//            try {
//                theEntityManagerFactory.close();
//                itsLogger.info("EntityManagerFactory closed.");
//            } catch (Exception e) {
//                itsLogger.log(Level.SEVERE, "Error while closing EntityManagerFactory", e);
//            }
//            theEntityManagerFactory = null;
//        }
//    }
//}

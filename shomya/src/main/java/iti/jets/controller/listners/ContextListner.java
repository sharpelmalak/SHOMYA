package iti.jets.controller.listners;

import iti.jets.util.Factory;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


@WebListener
public class ContextListner implements ServletContextListener{

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        Factory.closeEntityManagerFactory();
        System.out.println("EntityManagerFactory Destroyed");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ecommerce");
        sce.getServletContext().setAttribute("emf", Factory.getEntityMangerFactory());
        System.out.println("EntityManagerFactory created");

        System.out.println("MAX SIZE "+Factory.getMaxPoolSize());
        System.out.println("MIN SIZE "+Factory.getMinPoolSize());
    }


}

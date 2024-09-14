package iti.jets.controller.listners;

import iti.jets.dao.UserDao;
import iti.jets.model.Admin;
import iti.jets.util.ConnectionInstance;
import iti.jets.util.Factory;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Date;


@WebListener
public class ContextListner implements ServletContextListener{

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        Factory.closeEntityManagerFactory();

        // Close HikariDataSource
        Factory.closeHikariDataSource();

        // Log destruction
        System.out.println("EntityManagerFactory and HikariDataSource destroyed");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Initialize EntityManagerFactory
        Factory.getEntityMangerFactory(); // Ensures it's created

        // Initialize HikariDataSource
        Factory.getHikariDataSource(); // Ensures it's created

        // Set EntityManagerFactory in servlet context
        sce.getServletContext().setAttribute("emf", Factory.getEntityMangerFactory());

        // Log pool size information
        System.out.println("EntityManagerFactory created");
        System.out.println("MAX SIZE " + Factory.getMaxPoolSize());
        System.out.println("MIN SIZE " + Factory.getMinPoolSize());
        ConnectionInstance connectionInstance = new ConnectionInstance(Factory.getEntityMangerFactory());
        UserDao userDao = new UserDao(connectionInstance.getEntityManager());
        boolean isAdmin = userDao.checkUserName("admin");
        if(!isAdmin)
        {
            Admin admin = new Admin();
            admin.setName("admin");
            admin.setUsername("admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(userDao.hashPassword("admin"));
            admin.setHireDate(new Date(2022,04,4));

            if(userDao.save(admin))
            {
                System.out.println("Admin successfully added");
            }
            else
            {
                System.out.println("Admin not added");
            }
        }
        connectionInstance.closeEntityManager();

    }
}


package iti.jets;

import iti.jets.model.Admin;
import iti.jets.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
public class ConnectionPoolingTest {
    private static final SessionFactory sessionFactory;

    static {
        // Create the SessionFactory
        Configuration configuration = new Configuration().configure("META-INF/hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    }

    public static void main(String[] args) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            List<User> logins = session.createQuery("FROM user").list();
            for (User login : logins) {
                System.out.println("Username: " + login.getUsername());
                System.out.println("Password: " + login.getPassword());
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void closeSession(Session session) {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}

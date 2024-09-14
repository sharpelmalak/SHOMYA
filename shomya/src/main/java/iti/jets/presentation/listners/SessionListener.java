package iti.jets.presentation.listners;


import iti.jets.persistence.util.ConnectionInstance;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import java.util.logging.Logger;


@WebListener
public class SessionListener implements HttpSessionListener {
    private static final Logger logger = Logger.getLogger(SessionListener.class.getName());

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        // This method is called when a new session is created
        HttpSession session = event.getSession();
        logger.info("Session created: ID=" + session.getId());
        ConnectionInstance connectionInstance = new ConnectionInstance((EntityManagerFactory) session.getServletContext().getAttribute("emf"));
        session.setAttribute("userConnection", connectionInstance);

        // Example: Set an attribute in the session
       // event.getSession().setAttribute("user", "guest");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        // This method is called when a session is destroyed
        logger.info("Session destroyed: ID=" + event.getSession().getId());

        // Example: Perform cleanup, like removing session-specific data
        // Clean up any resources tied to this session if necessary
    }
}


package iti.jets.service;

import iti.jets.dao.UserDao;
import iti.jets.model.Admin;
import iti.jets.model.Customer;
import iti.jets.model.User;
import iti.jets.util.ConnectionInstance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;



public class AuthService {

    EntityManagerFactory emf;
    UserRole useRole;
    public enum UserRole{
        IS_ADMIN,
        IS_CUSTOMER
    };
    //EntityManager em;
    public AuthService(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    public User authUser(String username, String pass)
    {
        ConnectionInstance connectionInstance = new ConnectionInstance(emf);
        UserDao userDao  = new UserDao(connectionInstance.getEntityManager());
        User user = userDao.checkUserCredintials(username,userDao.hashPassword(pass));
        if (user==null)
            return null;
        else if (user instanceof Admin) {
            useRole = UserRole.IS_ADMIN;
        }
        else if (user instanceof Customer) {
            useRole = UserRole.IS_CUSTOMER;
        }
        return user;
    }
    public UserRole getRole()
    {
        return  useRole;
    }
}

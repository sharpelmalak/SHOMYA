package iti.jets.service;

import iti.jets.dao.UserDao;
import iti.jets.model.Admin;
import iti.jets.model.Customer;
import iti.jets.model.User;
import iti.jets.util.ConnectionInstance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;



public class AuthService {


    UserRole useRole;
    User user;
    public enum UserRole{
        IS_ADMIN,
        IS_CUSTOMER
    };
    //EntityManager em;
    public AuthService()
    {}
    public void authUser(String username, String pass,ConnectionInstance connectionInstance)
    {
         UserDao userDao  = new UserDao(connectionInstance.getEntityManager());
         user = userDao.checkUserCredintials(username,userDao.hashPassword(pass));
         if (user instanceof Admin) {
            useRole = UserRole.IS_ADMIN;
         }
        else if (user instanceof Customer) {
            useRole = UserRole.IS_CUSTOMER;
        }
    }

    public User getUser() {
        return user;
    }

    public UserRole getRole()
    {
        return  useRole;
    }
}

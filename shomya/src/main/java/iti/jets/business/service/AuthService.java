package iti.jets.business.service;

import iti.jets.persistence.dao.UserDao;
import iti.jets.persistence.model.Admin;
import iti.jets.persistence.model.Customer;
import iti.jets.persistence.model.User;
import iti.jets.persistence.util.ConnectionInstance;


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

    public static boolean isUserNameFound(String username,ConnectionInstance connectionInstance)
    {
        UserDao userDao  = new UserDao(connectionInstance.getEntityManager());
        if(userDao.checkUserName(username))
        {
            return true;
        }
        return false;
    }
    public static boolean isUserEmailFound(String email,ConnectionInstance connectionInstance)
    {
        UserDao userDao  = new UserDao(connectionInstance.getEntityManager());
        if(userDao.checkEmail(email))
        {
            return true;
        }
        return false;
    }
}

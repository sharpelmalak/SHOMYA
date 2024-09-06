package iti.jets.service;

import iti.jets.dao.UserDao;
import iti.jets.model.Customer;
import iti.jets.model.User;
import iti.jets.util.ConnectionInstance;
import jakarta.persistence.EntityManagerFactory;

import java.sql.Date;


public class RegisterationService {
    EntityManagerFactory emf;
    boolean isUser;
    User user;

    public RegisterationService(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    public User registerUser(String username, String password, String name, Date date, String job, String email, float creditLimit, String address)
    {
        ConnectionInstance connectionInstance = new ConnectionInstance(emf);
        UserDao userDao  = new UserDao(connectionInstance.getEntityManager());
        return user = userDao.Register(username, password, name, date, job, email, creditLimit, address);
    }
    public void AddingUserToDB(User user)
    {
        ConnectionInstance connectionInstance = new ConnectionInstance(emf);
        UserDao userDao  = new UserDao(connectionInstance.getEntityManager());
        userDao.save(user);
    }


//    public boolean checkUsername(String username)
//    {
//
//        ConnectionInstance connectionInstance = new ConnectionInstance(emf);
//        UserDao userDao  = new UserDao(connectionInstance.getEntityManager());
//         user =userDao.findByUsername(username) ;
//        if (user==null)
//        {
//            isUser=false;
//        }
//        else
//        {isUser=true;}
//
//        return isUser;
//    }





}

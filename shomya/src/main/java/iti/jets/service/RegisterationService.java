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


    private UserDao userDao;



    public boolean isUsernameUnique(String username) {
        return userDao.findByUsername(username) == null;
    }

    public boolean isEmailUnique(String email) {
        return userDao.findUserByEmail(email) == null;
    }



}

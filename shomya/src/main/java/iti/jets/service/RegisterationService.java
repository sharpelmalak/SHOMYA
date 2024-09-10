package iti.jets.service;

import iti.jets.dao.UserDao;
import iti.jets.model.Customer;
import iti.jets.model.User;
import iti.jets.util.ConnectionInstance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.sql.Date;


public class RegisterationService
{

    boolean isUser;
    User user;



    public boolean registerUser(String username, String password, String name,
                             Date date, String job, String email, float creditLimit,
                             String address,ConnectionInstance connectionInstance)
    {
        UserDao userDao  = new UserDao(connectionInstance.getEntityManager());
        Customer user = new Customer();
        user.setUsername(username);

        // Hash the password using SHA-256
        String hashedPassword = userDao.hashPassword(password);
        user.setPassword(hashedPassword);

        user.setName(name);
        user.setBirthdate(date);
        user.setJob(job);
        user.setEmail(email);
        user.setCreditLimit(creditLimit);
        user.setAddress(address);


        return userDao.save(user);
    }





        public boolean isUsernameUnique(String username, EntityManager entityManager)
    {
        UserDao userDao  = new UserDao(entityManager);
        return userDao.findByUsername(username) == null;
    }

    public boolean isEmailUnique(String email,EntityManager entityManager) {
        UserDao userDao  = new UserDao(entityManager);
        return userDao.findUserByEmail(email) == null;
    }



}

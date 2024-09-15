package iti.jets.business.service;

import iti.jets.persistence.dao.UserDao;
import iti.jets.persistence.model.Customer;
import iti.jets.persistence.model.User;
import jakarta.persistence.EntityManager;

import java.sql.Date;


public class RegisterationService
{

    public Customer registerUser(String username, String password, String name,
                             Date date, String job, String email, float creditLimit,
                             String address,EntityManager entityManager)
    {
        UserDao userDao  = new UserDao(entityManager);
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

if(userDao.save(user)){
    return user;
}
else
{
    return null;
}


    }



}

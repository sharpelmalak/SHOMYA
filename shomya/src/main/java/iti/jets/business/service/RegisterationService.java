package iti.jets.business.service;

import iti.jets.business.dto.CategoryDTO;
import iti.jets.persistence.dao.CategoryDao;
import iti.jets.persistence.dao.UserDao;
import iti.jets.persistence.model.Category;
import iti.jets.persistence.model.Customer;
import iti.jets.persistence.model.User;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.persistence.EntityManager;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class RegisterationService
{

    public static boolean registerUser(String username, String password, String name,
                                       Date date, String job, String email, float creditLimit,
                                       String address, String[] selectedCategories,ConnectionInstance connectionInstance)
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
        if(selectedCategories!=null && selectedCategories.length>0)
        {
            CategoryDao categoryDao = new CategoryDao(connectionInstance.getEntityManager());
            List<Category> categories = categoryDao.findAll();
            List<Category> userCategories = categories.stream().
                    filter(category -> {for (String id : selectedCategories)
                    {
                        if (category.getId() == Integer.parseInt(id)) {
                            return true; } } return false; }).collect(Collectors.toList());
            Set<Category> categorySet=new HashSet<>(userCategories);
            user.setCategories(categorySet);
        }

        if(userDao.save(user)){
            return true;
        }
        return false;
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



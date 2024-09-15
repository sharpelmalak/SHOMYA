package iti.jets.business.service;

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

    public boolean registerUser(String username, String password, String name,
                                 Date date, String job, String email, float creditLimit,
                                 String address, String[] selectedCategories, ConnectionInstance connectionInstance)
    {

        Customer user = new Customer();
        user.setUsername(username);
        user.setName(name);
        user.setBirthdate(date);
        user.setJob(job);
        user.setEmail(email);
        user.setCreditLimit(creditLimit);
        user.setAddress(address);
        UserDao userDao  = new UserDao(connectionInstance.getEntityManager());
        user.setPassword(userDao.hashPassword(password));
        if(userDao.save(user)){
            if(selectedCategories!=null && selectedCategories.length>0)
            {
                try {
                    EntityManager entityManager = connectionInstance.getEntityManager();

                    entityManager.getTransaction().begin();
                    List<Category> userCategories = CategoryService.getCategories(connectionInstance).stream().
                            filter(category -> {for (String id : selectedCategories)
                            {
                                if (category.getId() == Integer.parseInt(id)) {
                                    return true; } } return false; }) .collect(Collectors.toList());

                    Set<Category> categorySet=new HashSet<>(userCategories);
                    user.setCategories(categorySet);
                    entityManager.merge(user);
                    entityManager.getTransaction().commit();
                }
                catch (Exception e) {
                    return false;
                }

            }
            return true;
        }
        else
        {
            return false;
        }

    }


}

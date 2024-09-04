package iti.jets.dao;

import iti.jets.model.User;
import jakarta.persistence.Query;

import java.util.List;

public class UserDao extends DAO<User,Integer>{
    public UserDao() {}

    @Override
    public List<User> search(User criteria) {
        return List.of();
    }
    public User checkUserCredintials(String uname,String pass)
    {
        User user;
        getConnection();
        try {

            Query q = entityManager.createQuery("from User u where u.username=:name and u.password=:pass").setParameter(
                    "name", uname).setParameter("pass", pass);
            user = (User)q.getSingleResult();
            entityManager.close();

        } catch (Exception e) {
            // TODO: handle exception
            user = null;
        }
        closeConnection();
        return user;
    }

 // Find User by userName
    public User findByUsername(String username)
    {
        User user = null;
        getConnection();
        try {
            user = entityManager.createQuery("FROM User WHERE username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error finding user with username: " + username, e);
        }
        closeConnection();
        return user;
    }







}

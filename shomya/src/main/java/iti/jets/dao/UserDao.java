package iti.jets.dao;

import iti.jets.model.Customer;
import iti.jets.model.User;
import iti.jets.util.ConnectionInstance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

import java.util.Base64;
import java.util.List;
import java.util.logging.Level;

public class UserDao extends DAO<User,Integer>{


    public UserDao(EntityManager entityManager) {
        super(entityManager);
    }

    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            // Handle hashing algorithm not found exception
            return null;
        }
    }

    @Override
    public List<User> search(User criteria) {
        return List.of();
    }

    public User checkUserCredintials(String uname,String pass)
    {
        User user;
        try {

            Query q = entityManager.createQuery("from User u where u.username=:name and u.password=:pass").setParameter(
                    "name", uname).setParameter("pass", pass);
            user = (User)q.getSingleResult();
            entityManager.close();

        } catch (Exception e) {
            // TODO: handle exception
            user = null;
        }
        return user;
    }
    public Customer Register(String uname, String pass, String name, Date birthday, String job, String email, float creditLimit, String address)
    {
        Customer user = new Customer();
        user.setUsername(uname);

        // Hash the password using SHA-256
        String hashedPassword = hashPassword(pass);
        user.setPassword(hashedPassword);

        user.setName(name);
        user.setBirthdate(birthday);
        user.setJob(job);
        user.setEmail(email);
        user.setCreditLimit(creditLimit);
        user.setAddress(address);

        return user;
    }

 // Find User by userName
    public User findByUsername(String username)
    {
        User user = null;

        try {
            user = entityManager.createQuery("FROM User WHERE username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error finding user with username: " + username, e);
        }

        return user;
    }



    public User findUserByEmail(String email) {
        try {
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            return query.getResultStream().findFirst().orElse(null);
        } finally {
            entityManager.close();
        }
    }

    public boolean checkUserName(String username)
    {
        boolean result;
        try {

            Query q = entityManager.createQuery("from User u where u.username=:name").setParameter(
                    "name", username);
            User user = (User)q.getSingleResult();
            result = true;
            entityManager.close();

        } catch (Exception e) {
            // TODO: handle exception
            result = false;
        }

        return result;
    }


    public boolean checkEmail(String email)
    {
        boolean result;
        try {

            Query q = entityManager.createQuery("from User u where u.email=:email").setParameter(
                    "email", email);
            User user = (User)q.getSingleResult();
            result = true;
            entityManager.close();

        } catch (Exception e) {
            // TODO: handle exception
            result = false;
        }

        return result;
    }

}

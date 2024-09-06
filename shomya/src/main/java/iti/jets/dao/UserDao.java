package iti.jets.dao;

import iti.jets.model.Customer;
import iti.jets.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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
//  public boolean validateCredentials(String uname, String pass) {
//        getConnection();
//
//        try {
//            // Retrieve the user by username
//            Query q = entityManager.createQuery("from User u where u.username = :name").setParameter("name", uname);
//            List<User> users = q.getResultList();
//
//            if (!users.isEmpty()) {
//                User user = users.get(0);
//                // Hash the entered password
//                String hashedEnteredPassword = hashPassword(pass);
//
//                // Compare the hashed entered password with the stored hashed password
//                if (hashedEnteredPassword.equals(user.getPassword())) {
//                    // Passwords match, credentials are valid
//                    return true;
//                }
//            }
//        } catch (Exception e) {
//            // Handle exception
//        }
//
//        closeConnection();
//        return false;
//    }



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


//        try {
//            // Check if the username already exists
//            Query q = entityManager.createQuery("from User u where u.username = :name").setParameter("name", uname);
//            List<User> existingUsers = q.getResultList();
//
//            if (existingUsers.isEmpty()) {
//                // Username is available, proceed with registration
//                entityManager.getTransaction().begin();
//                entityManager.persist(user);
//                entityManager.getTransaction().commit();
//            } else {
//                // Username already exists, present a message
//                System.out.println("Username already exists. Please choose a different username.");
//                user = null;
//            }
//            entityManager.close();
//        } catch (Exception e) {
//            // Handle exception
//            user = null;
//        }
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








}

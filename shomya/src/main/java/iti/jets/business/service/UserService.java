package iti.jets.business.service;

import iti.jets.persistence.dao.CategoryDao;
import iti.jets.persistence.dao.CustomerDao;
import iti.jets.persistence.dao.UserDao;
import iti.jets.persistence.model.*;
import iti.jets.persistence.util.ConnectionInstance;

import java.util.Iterator;
import java.util.List;

public class UserService {

    public static User getUserById(ConnectionInstance connectionInstance, int id) {
        UserDao userDao = new UserDao(connectionInstance.getEntityManager());
        return userDao.findById(id);
    }

    public static List<User> getCustomers(ConnectionInstance connectionInstance) {
        UserDao userDao = new UserDao(connectionInstance.getEntityManager());
        List<User> customerList = userDao.findAll();
        Iterator<User> iterator = customerList.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user instanceof Admin) {
                iterator.remove();  // Safely removes the Admin user
            }
        }
        return customerList;
    }
    public static List<Category> getInterests(ConnectionInstance connection,int id) {
        CustomerDao dao = new CustomerDao(connection.getEntityManager());
        return dao.findInterests(id);
    }
    public static List<Order> getCustomerOrders(ConnectionInstance connection, int id) {
        CustomerDao dao = new CustomerDao(connection.getEntityManager());
        return dao.getOrdersByCustomerId(id);
    }


}

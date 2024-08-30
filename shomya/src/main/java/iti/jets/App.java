package iti.jets;

import iti.jets.dao.UserDao;
import iti.jets.model.Admin;
import iti.jets.model.Customer;
import iti.jets.model.Order;
import iti.jets.model.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) {

//        Customer customer = new Customer();
//        customer.setName("John Doe");
//        customer.setUsername("JohnDoe");
//        customer.setEmail("john.doe@gmail.com");
//        customer.setPassword("1234");
//        customer.setBirthdate(new Date(2022,04,4));
//        customer.setAddress("22 b sherman");
//        customer.setCreditLimit(5000F);
//        customer.setJob("wear");
//        UserDao userDao = new UserDao();
//        if(userDao.save(customer))
//        {
//            System.out.println("Customer successfully added");
//        }
//        else
//        {
//            System.out.println("Customer not added");
//        }

//        Admin admin = new Admin();
//        admin.setName("John Doe");
//        admin.setUsername("JohnDoe");
//        admin.setEmail("john.doe@gmail.com");
//        admin.setPassword("1234");
//        admin.setHireDate(new Date(2022,04,4));
//
//        UserDao userDao = new UserDao();
//        if(userDao.save(admin))
//        {
//            System.out.println("Admin successfully added");
//        }
//        else
//        {
//            System.out.println("Admin not added");
//        }
//        UserDao userDao = new UserDao();
//        User user = userDao.checkUserCredintials("JohnDoe","1234");
//        System.out.println(user);
//        if (user instanceof Admin) {
//            System.out.println("Admin User");
//            Admin admin = (Admin) user;
//        }
//        else if (user instanceof Customer)
//        {
//            System.out.println("Customer User");
//            Customer customer = (Customer) user;
//        }
//        else
//        {
//            System.out.println("Invalid User");
//        }
//        List<Order> orders=new ArrayList<>();
//
//
//        UserDao userDao= new UserDao();
//        Customer user = (Customer) userDao.findById(1);
//        if (user != null) {
//            System.out.println(user.getJob());
//            Set<Order> orderSet= user.getOrders();
//            //List<Order> orders = (List<Order>) user.getOrders();
//            for(Order order:orderSet) {
//                orders.add(order);
//                System.out.println(order.getOrderDate());
//            }
//        }
//        else{
//            System.out.println("no such user found");
//        }


    }
}

//package iti.jets;
//
//import com.mchange.v2.c3p0.ComboPooledDataSource;
//
//import java.awt.print.Book;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import com.mchange.v2.c3p0.ComboPooledDataSource;
//import iti.jets.dao.AdminDao;
//import iti.jets.dao.UserDao;
//import iti.jets.model.*;
//import iti.jets.util.ConnectionInstance;
//import iti.jets.util.Factory;
//import jakarta.persistence.EntityManager;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//public class App {
//   public static void main(String[] args) {
//
////     // ConnectionInstance connectionInstance = null;
////
////        Customer customer = new Customer();
////        customer.setName("John Doe");
////        customer.setUsername("JohnDoe");
////        customer.setEmail("john.doe@gmail.com");
////        customer.setPassword("1234");
////        customer.setBirthdate(new Date(2022,04,4));
////        customer.setAddress("22 b sherman");
////        customer.setCreditLimit(5000F);
////        customer.setJob("wear");
////        UserDao userDao = new UserDao(connectionInstance.getEntityManager());
////        if(userDao.save(customer))
////        {
////            System.out.println("Customer successfully added");
////        }
////        else
////        {
////            System.out.println("Customer not added");
////        }
////
////        Admin admin = new Admin();
////        admin.setName("John Doe");
////        admin.setUsername("JohnDoe");
////        admin.setEmail("john.doe@gmail.com");
////        admin.setPassword("1234");
////        admin.setHireDate(new Date(2022,04,4));
////
////       // UserDao userDao = new UserDao();
////        if(userDao.save(admin))
////        {
////            System.out.println("Admin successfully added");
////        }
////        else
////        {
////            System.out.println("Admin not added");
////        }
////        UserDao userDao = new UserDao();
////        User user = userDao.checkUserCredintials("JohnDoe","1234");
////        System.out.println(user);
////        if (user instanceof Admin) {
////            System.out.println("Admin User");
////            Admin admin = (Admin) user;
////        }
////        else if (user instanceof Customer)
////        {
////            System.out.println("Customer User");
////            Customer customer = (Customer) user;
////        }
////        else
////        {
////            System.out.println("Invalid User");
////        }
////        List<Order> orders=new ArrayList<>();
////
////
////        UserDao userDao= new UserDao();
////        Customer user = (Customer) userDao.findById(1);
////        if (user != null) {
////            System.out.println(user.getJob());
////            Set<Order> orderSet= user.getOrders();
////            //List<Order> orders = (List<Order>) user.getOrders();
////            for(Order order:orderSet) {
////                orders.add(order);
////                System.out.println(order.getOrderDate());
////            }
////        }
////        else{
////            System.out.println("no such user found");
////        }
//
//
////        Customer customer = new Customer();
////        customer.setName("John Doe");
////        customer.setUsername("JohnDoe");
////        customer.setEmail("john.doe@gmail.com");
////        customer.setPassword("1234");
////        customer.setBirthdate(new Date(2022,04,4));
////        customer.setAddress("22 b sherman");
////        customer.setCreditLimit(5000F);
////        customer.setJob("wear");
//
//
////        String name =  customer.getName();
////        String username = customer.getUsername();
////        String email = customer.getEmail();
////        String password =customer.getPassword();
////        Date birthdate = customer.getBirthdate();
////        String address =customer.getAddress();
////        float cl=customer.getCreditLimit();
////        String job =customer.getJob();
////
////        Customer customer1 =new Customer(name,username,email,password,birthdate,address,cl,job);
//
//
//       // connection pooling
//
////
////
////    static ComboPooledDataSource comboPooledDataSource = null;
////
////    static {
////        comboPooledDataSource = new ComboPooledDataSource();
////        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3366/mydb");
////        comboPooledDataSource.setUser("root");
////        comboPooledDataSource.setPassword("4520");
////        comboPooledDataSource.setMinPoolSize(3);
////        comboPooledDataSource.setAcquireIncrement(3);
////        comboPooledDataSource.setMaxPoolSize(30);
////
////    }
////
////            public static void main(String[] args) throws SQLException {
////                Connection connection = null;
////                Statement statement = null;
////                ResultSet resultSet = null;
////                try {
////                    connection = comboPooledDataSource.getConnection();
////                    statement = connection.createStatement();
////                    resultSet = statement.executeQuery("select * from login");
////                    while (resultSet.next()) {
////                        System.out.println("username " + resultSet.getString("username"));
////                        System.out.println("password " + resultSet.getString("password"));
////                       }
////                } finally {
////                    resultSet.close();
////                    statement.close();
////                    connection.close();
////                }
////            }
//
//
//       ConnectionInstance connectionInstance=new ConnectionInstance(Factory.getEntityMangerFactory());
//
//       UserDao userDao = new UserDao(connectionInstance.getEntityManager());
//
//       //Customer customer = new Customer();
//       Customer customer = (Customer) userDao.findByUsername("customer11");
//       System.out.println(customer.getJob());
//
////       customer.setName("customer11");
////       customer.setUsername("customer11");
////       customer.setEmail("customer11@gmail.com");
////       customer.setPassword(userDao.hashPassword("1234"));
////       customer.setBirthdate(new Date(2022,04,4));
////       customer.setAddress("22 b sherman");
////       customer.setCreditLimit(5000F);
////       customer.setJob("wear");
////
////       if(userDao.save(customer))
////       {
////           System.out.println("Customer successfully added");
////       }
////       else
////       {
////           System.out.println("Customer not added");
////       }
//
//
//
////       Admin admin = new Admin();
////        admin.setName("admin111");
////        admin.setUsername("admin111");
////        admin.setEmail("admin111@gmail.com");
////        admin.setPassword(userDao.hashPassword("1234"));
////        admin.setHireDate(new Date(2022,04,4));
////
////        if(userDao.save(admin))
////        {
////            System.out.println("Admin successfully added");
////        }
////        else
////        {
////            System.out.println("Admin not added");
////        }
//////        UserDao userDao = new UserDao();
////        User user = userDao.checkUserCredintials("customer", userDao.hashPassword("1234"));
////        System.out.println(user);
////        if (user instanceof Admin) {
////            System.out.println("Admin User");
////            admin = (Admin) user;
////        }
////        else if (user instanceof Customer)
////        {
////            System.out.println("Customer User");
////            customer = (Customer) user;
////        }
////        else
////        {
////            System.out.println("Invalid User");
////        }
//
//
////       Admin user = (Admin)userDao.findById(2);
////       if (user instanceof Admin) {
////            System.out.println("Admin User");
////        }
////       else
////        {
////            System.out.println("Invalid User");
////        }
////
////           AdminDao adminDao=new AdminDao(connectionInstance.getEntityManager());
////       Category book = adminDao.getCategory(3);
////       if (book instanceof Category) {
////           System.out.println("Book Category");
////       }
////       else
////       {
////           System.out.println("Invalid Category");
////       }
////       Product product = new Product(user,book,"Tales of two cities",50.50F,10,"This is the book for language schoools","/resource/tales.jpg");
////       Product product1 = new Product(user,book,"Journey to the center of the earth",29.19F,50,"this book is amazing","/resource/center.jpg");
////        adminDao.addProduct(product);
////        adminDao.addProduct(product1);
////        List<Customer> customers = adminDao.getAllCustomers();
////        for (Customer customer : customers) {
////            System.out.println(customer.getAddress());
////            System.out.println(customer.getName());
////        }
////        List<Product> products = adminDao.getallProducts();
////        for (Product product : products) {
////            System.out.println(product.getName());
////        }
//       //       Category category=new Category("cloths");
////       Category category2=new Category("electronics");
////       Category category3=new Category("books");
//
////       adminDao.addCategory(category);
////       adminDao.addCategory(category2);
////       adminDao.addCategory(category3);
//
//
//
//       connectionInstance.closeEntityManager();
//       Factory.closeEntityManagerFactory();
//
//
//   }
//        }
//

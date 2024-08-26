package iti.jets;

import iti.jets.model.Category;
import iti.jets.model.Order;
import iti.jets.model.Product;
import iti.jets.model.User;
import iti.jets.service.CategoryService;
import iti.jets.service.OrderService;
import iti.jets.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) {

//        Category category = new Category("Books");
//        Category category1 = new Category("LAPS");
//        Category category2 = new Category("COOKS");
//        Category category3 = new Category("FASHOIN");
//        Category category4 = new Category("CLOTHES");
//
//         CategoryService categoryService = new CategoryService();
//         categoryService.addCategory(category);
//         categoryService.addCategory(category1);
//         categoryService.addCategory(category2);
//         categoryService.addCategory(category3);
//         categoryService.addCategory(category4);
//
//         List<Category> ls = categoryService.getAllCategories();
//        for (Category c : ls) {
//            System.out.println(c.getName());
//        }
//
//
//        Product product1 = new Product();
//        product1.setName("Product 2");
//        product1.setPrice(500F);
//        product1.setQuantity(10);
//
//        ProductService productService = new ProductService();
//        productService.addProduct(product1);///////
//        Product p = productService.getProduct(1);
//        Set<Category> set = new HashSet<>();
//        set.add(ls.get(0));
//        p.setCategories(set);
//        productService.updateProduct(p);

//          productService.addProduct(product1);
//          Set<Category> set = new HashSet<>(ls);
//          product1.setCategories(set);
//          productService.updateProduct(product1);
//        List<Product> lsp = categoryService.getProductsOfCategoryById(ls.get(0).getId());
//        for (Product p : lsp) {
//            System.out.println(p.getName());
//        }

//        /* test code*/
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecommerce");
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
////        Category cat = new Category("Books");
////        Category cat1 = new Category("Sports");
////        Category cat2 = new Category("Electronics");
////        Category cat3 = new Category("Cooking");
////        Set<Category> st = new HashSet<>();
////        st.add(cat);
////        st.add(cat1);
////        em.persist(cat);
////        em.persist(cat1);
////        em.persist(cat2);
////        em.persist(cat3);
//
//        User user = new User();
//        user.setUsername("yasser12");
//        user.setPassword("Jets");
//        user.setBirthdate(new Date(1997,12,8));
//        user.setAddress("7 at papa");
//        user.setEmail("email@gmail.com");
//        user.setName("Yasser");
//        user.setCreditLimit(2000F);
//        User user1 = new User();
//        user1.setUsername("yassmenn");
//        user1.setPassword("Jets");
//        user1.setBirthdate(new Date(1997,12,8));
//        user1.setAddress("Fayom");
//        user1.setEmail("email@gmail.com");
//        user1.setName("Yasmeen");
//        user1.setCreditLimit(2000F);
//        em.persist(user1);
//        em.getTransaction().commit();
//        em.close();
//        emf.close();


        OrderService orderservice = new OrderService();
        


    }
}

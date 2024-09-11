package iti.jets.service;

import iti.jets.dao.CartItemDao;
import iti.jets.dao.ProductDao;
import iti.jets.model.CartItem;
import iti.jets.model.Customer;
import iti.jets.model.Product;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CartService {
    public static CartItem addProductToCart(Customer customer, int productId,int quantity, EntityManager entityManager)
    {
        CartItem result = null;
        try{
            ProductDao productDao = new ProductDao(entityManager);
            Product product = productDao.findById(productId);
            if(product == null || product.getQuantity() < quantity)
            {
                throw new Exception("Product not available");
            }
            else
            {
                result = new CartItem(customer, product, quantity);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static boolean chekProductInCart(List<CartItem> cart,int productId,int quantity)
    {
        boolean result = false;
        for (CartItem cartItem : cart) {
            if(cartItem.getProduct().getId() == productId)
            {
                cartItem.setQuantity(quantity);
                result = true;
            }
        }
        return result;
    }
    public static void chekCart(List<CartItem> cart, EntityManager entityManager) {
        try {
            ProductDao productDao = new ProductDao(entityManager);
            // Use an Iterator to avoid ConcurrentModificationException
            Iterator<CartItem> iterator = cart.iterator();

            while (iterator.hasNext()) {
                CartItem cartItem = iterator.next();
                Product product = productDao.findById(cartItem.getProduct().getId());

                // If the product exists and there's enough quantity
                if (product != null && product.getQuantity() >= cartItem.getQuantity()) {
                    // everything is okay, continue
                } else {
                    // Product is either deleted or out of stock, remove the cart item
                    iterator.remove(); // Safely remove the item from the list
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeFromCart(List<CartItem> cart,int id) {
        try {

            // Use an Iterator to avoid ConcurrentModificationException
            Iterator<CartItem> iterator = cart.iterator();

            while (iterator.hasNext()) {
                CartItem cartItem = iterator.next();

                // If the product exists and there's enough quantity
                if (cartItem.getProduct().getId() == id) {
                    iterator.remove();
                    break;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveCart(List<CartItem> cart, EntityManager entityManager)
    {

        try{
            entityManager.getTransaction().begin();
            for (CartItem cartItem : cart) {
                entityManager.persist(cartItem);
            }
            entityManager.getTransaction().commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public static List<CartItem> loadCart(int customerId,EntityManager entityManager)
    {
        List<CartItem> cart = null;
        try{
            CartItemDao cartItemDao = new CartItemDao(entityManager);
            cart =  cartItemDao.findAll(customerId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return cart;
    }
    public static void resetCart(int customerId,EntityManager entityManager)
    {
        CartItemDao cartItemDao = new CartItemDao(entityManager);
        cartItemDao.deleteAll(customerId);
    }

}

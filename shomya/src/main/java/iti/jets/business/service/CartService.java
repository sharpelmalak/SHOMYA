package iti.jets.business.service;

import iti.jets.persistence.dao.CartItemDao;
import iti.jets.persistence.dao.ProductDao;
import iti.jets.persistence.model.CartItem;
import iti.jets.persistence.model.Customer;
import iti.jets.persistence.model.Product;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.persistence.EntityManager;

import java.util.Iterator;
import java.util.List;

public class CartService {
    public static CartItem addProductToCart(Customer customer, int productId, int quantity, ConnectionInstance connectionInstance)
    {
        CartItem result = null;
        try{
            ProductDao productDao = new ProductDao(connectionInstance.getEntityManager());
            Product product = productDao.findById(productId);
            if(product == null || product.getQuantity() < quantity || product.getQuantity()==0)
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

    public static boolean checkProductInCart(List<CartItem> cart,int productId,int quantity)
    {
        boolean result = false;
        for (CartItem cartItem : cart) {
            if(cartItem.getProduct().getId() == productId)
            {
                if(quantity == 1)quantity+= cartItem.getQuantity();
                cartItem.setQuantity(quantity);
                result = true;
            }
        }
        return result;
    }

    public static float calculateTotalCart(List<CartItem> cart)
    {
        float result = 0F;
        for (CartItem cartItem : cart) {
            result += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        return result;
    }
    public static boolean checkCart(List<CartItem> cart, ConnectionInstance connectionInstance) {
        boolean result = false;
        try {
            ProductDao productDao = new ProductDao(connectionInstance.getEntityManager());
            // Use an Iterator to avoid ConcurrentModificationException
            Iterator<CartItem> iterator = cart.iterator();

            while (iterator.hasNext()) {
                CartItem cartItem = iterator.next();
                Product product = productDao.findById(cartItem.getProduct().getId());

                // If the product exists and there's enough quantity
                if (product == null || product.getQuantity()==0) {
                    result = true;
                    iterator.remove();
                } else {
                    if(!cartItem.getProduct().equals(product)){
                        if(product.getQuantity() < cartItem.getQuantity()){
                            cartItem.setQuantity(product.getQuantity());
                            result = true;
                        }
                        else if(product.getPrice() != cartItem.getProduct().getPrice()){
                            result = true;
                        }
                        cartItem.setProduct(product);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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

    public static void saveCart(List<CartItem> cart, ConnectionInstance connectionInstance)
    {

        EntityManager entityManager = connectionInstance.getEntityManager();
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

    public static List<CartItem> loadCart(int customerId,ConnectionInstance connectionInstance)
    {
        List<CartItem> cart = null;
        try{
            CartItemDao cartItemDao = new CartItemDao(connectionInstance.getEntityManager());
            cart =  cartItemDao.findAll(customerId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return cart;
    }
    public static void resetCart(int customerId,ConnectionInstance connectionInstance)
    {
        CartItemDao cartItemDao = new CartItemDao(connectionInstance.getEntityManager());
        cartItemDao.deleteAll(customerId);
    }

}

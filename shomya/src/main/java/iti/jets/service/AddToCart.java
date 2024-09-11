package iti.jets.service;

import iti.jets.dao.ProductDao;
import iti.jets.model.CartItem;
import iti.jets.model.Customer;
import iti.jets.model.Product;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AddToCart {
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
}

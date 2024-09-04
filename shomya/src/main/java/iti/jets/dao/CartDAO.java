package iti.jets.dao;

import iti.jets.model.CartItem;
import iti.jets.model.Cart;
import iti.jets.model.Customer;
import iti.jets.model.Product;

import jakarta.persistence.PersistenceException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CartDAO extends DAO<CartItem, Integer> {

    private static final Logger LOGGER = Logger.getLogger(CartDAO.class.getName());

    // ************************SEARCH*********************************** //
    @Override
    public List<CartItem> search(CartItem criteria) {
        return null;
    }

    // ******************************* Add item to cart *****************************************************
    public boolean addItemToCart(Customer customer, Product product, int quantity) {
        boolean result = false;
        getConnection();
        try {
            entityManager.getTransaction().begin();

            // Check if the product is already in the cart
            CartItem existingItem = entityManager.createQuery(
                            "SELECT ci FROM CartItem ci WHERE ci.product = :product AND ci.cart.customer = :customer", CartItem.class)
                    .setParameter("product", product)
                    .setParameter("customer", customer)
                    .getSingleResult();

            if (existingItem != null) {
                // If the item is already in the cart, update the quantity
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
                entityManager.merge(existingItem);
            } else {
                // If the item is not in the cart, create a new cart item
                CartItem cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setQuantity(quantity);
                cartItem.setCurrentPrice(product.getPrice());

                // Find or create the cart for the customer
                Cart cart = entityManager.find(Cart.class, customer.getId());
                if (cart == null) {
                    cart = new Cart();
                    cart.setCustomer(customer);
                    entityManager.persist(cart);
                }

                cart.addCartItem(cartItem);
                entityManager.persist(cartItem);
            }

            entityManager.getTransaction().commit();
            result = true;
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            LOGGER.log(Level.SEVERE, "Error adding item to cart", e);
        }
        closeConnection();
        return result;
    }

    //************************************************* Remove item from cart***********************************************
    public boolean removeItemFromCart(Customer customer, Product product) {
        boolean result = false;
        getConnection();
        try {
            entityManager.getTransaction().begin();

            CartItem cartItem = entityManager.createQuery(
                            "SELECT ci FROM CartItem ci WHERE ci.product = :product AND ci.cart.customer = :customer", CartItem.class)
                    .setParameter("product", product)
                    .setParameter("customer", customer)
                    .getSingleResult();

            if (cartItem != null) {
                entityManager.remove(cartItem);
                result = true;
            }

            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            LOGGER.log(Level.SEVERE, "Error removing item from cart", e);
        }
        closeConnection();
        return result;
    }

    //***************************************** Get all items in the cart**********************************************
    public List<CartItem> getCartItems(Customer customer) {
        List<CartItem> cartItems = null;
        getConnection();
        try {
            cartItems = entityManager.createQuery(
                            "SELECT ci FROM CartItem ci WHERE ci.cart.customer = :customer", CartItem.class)
                    .setParameter("customer", customer)
                    .getResultList();
        } catch (PersistenceException e) {
            LOGGER.log(Level.SEVERE, "Error getting cart items", e);
        }
        closeConnection();
        return cartItems;
    }

    //****************************************** Clear the cart after purchase*********************************************
    public boolean clearCart(Customer customer) {
        boolean result = false;
        getConnection();
        try {
            entityManager.getTransaction().begin();

            List<CartItem> cartItems = entityManager.createQuery(
                            "SELECT ci FROM CartItem ci WHERE ci.cart.customer = :customer", CartItem.class)
                    .setParameter("customer", customer)
                    .getResultList();

            for (CartItem cartItem : cartItems) {
                entityManager.remove(cartItem);
            }

            entityManager.getTransaction().commit();
            result = true;
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            LOGGER.log(Level.SEVERE, "Error clearing cart", e);
        }
        closeConnection();
        return result;
    }
}

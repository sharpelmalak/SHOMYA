package iti.jets.model;
// Generated Sep 2, 2024, 5:22:05 PM by Hibernate Tools 6.5.1.Final


import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * CartItem generated by hbm2java
 */
@Entity
@Table(name="cart_item"
    ,catalog="shomya"
)
public class CartItem  implements java.io.Serializable {


     private CartItemId id;
     private Product product;
     private Customer customer;
     private int quantity;

    public CartItem() {
    }

    public CartItem(CartItemId id, Product product, Customer customer, int quantity) {
       this.id = id;
       this.product = product;
       this.customer = customer;
       this.quantity = quantity;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="customerId", column=@Column(name="customer_id", nullable=false) ), 
        @AttributeOverride(name="productId", column=@Column(name="product_id", nullable=false) ) } )
    public CartItemId getId() {
        return this.id;
    }
    
    public void setId(CartItemId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id", nullable=false, insertable=false, updatable=false)
    public Product getProduct() {
        return this.product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customer_id", nullable=false, insertable=false, updatable=false)
    public Customer getCustomer() {
        return this.customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    
    @Column(name="quantity", nullable=false)
    public int getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }




}



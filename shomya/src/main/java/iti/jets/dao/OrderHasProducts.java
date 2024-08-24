package iti.jets.dao;
// Generated Aug 24, 2024, 11:37:30 AM by Hibernate Tools 6.5.1.Final


import jakarta.persistence.*;

/**
 * OrderHasProducts generated by hbm2java
 */
@Entity
@Table(name="order_has_products"
    ,catalog="shomya"
)
public class OrderHasProducts  implements java.io.Serializable {


     private OrderHasProductsId id;
     private Order order;
     private Product product;
     private int quantity;
     private float currentPrice;

    public OrderHasProducts() {
    }

    public OrderHasProducts(OrderHasProductsId id, Order order, Product product, int quantity, float currentPrice) {
       this.id = id;
       this.order = order;
       this.product = product;
       this.quantity = quantity;
       this.currentPrice = currentPrice;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="orderId", column=@Column(name="order_id", nullable=false) ), 
        @AttributeOverride(name="productId", column=@Column(name="product_id", nullable=false) ) } )
    public OrderHasProductsId getId() {
        return this.id;
    }
    
    public void setId(OrderHasProductsId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_id", nullable=false, insertable=false, updatable=false)
    public Order getOrder() {
        return this.order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id", nullable=false, insertable=false, updatable=false)
    public Product getProduct() {
        return this.product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }

    
    @Column(name="quantity", nullable=false)
    public int getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    @Column(name="current_price", nullable=false, precision=12)
    public float getCurrentPrice() {
        return this.currentPrice;
    }
    
    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }




}



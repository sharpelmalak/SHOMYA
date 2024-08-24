package iti.jets.dao;
// Generated Aug 24, 2024, 11:37:30 AM by Hibernate Tools 6.5.1.Final


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * OrderHasProductsId generated by hbm2java
 */
@Embeddable
public class OrderHasProductsId  implements java.io.Serializable {


     private int orderId;
     private int productId;

    public OrderHasProductsId() {
    }

    public OrderHasProductsId(int orderId, int productId) {
       this.orderId = orderId;
       this.productId = productId;
    }
   


    @Column(name="order_id", nullable=false)
    public int getOrderId() {
        return this.orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    @Column(name="product_id", nullable=false)
    public int getProductId() {
        return this.productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof OrderHasProductsId) ) return false;
		 OrderHasProductsId castOther = ( OrderHasProductsId ) other; 
         
		 return (this.getOrderId()==castOther.getOrderId())
 && (this.getProductId()==castOther.getProductId());
   }
   
   public int hashCode() {
         int result = 17;
         
        result = 37 * result + this.getOrderId();
        result = 37 * result + this.getProductId();
         return result;
   }   


}



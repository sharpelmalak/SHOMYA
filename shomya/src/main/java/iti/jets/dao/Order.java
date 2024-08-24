package iti.jets.dao;
// Generated Aug 24, 2024, 11:37:30 AM by Hibernate Tools 6.5.1.Final


import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Order generated by hbm2java
 */
@Entity
@Table(name="order"
    ,catalog="shomya"
)
public class Order  implements java.io.Serializable {


     private int id;
     private User user;
     private Timestamp orderDate;
     private Set<OrderHasProducts> orderHasProductses = new HashSet<OrderHasProducts>(0);

    public Order() {
    }

	
    public Order(int id, User user, Timestamp orderDate) {
        this.id = id;
        this.user = user;
        this.orderDate = orderDate;
    }
    public Order(int id, User user, Timestamp orderDate, Set<OrderHasProducts> orderHasProductses) {
       this.id = id;
       this.user = user;
       this.orderDate = orderDate;
       this.orderHasProductses = orderHasProductses;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="order_date", nullable=false, length=19)
    public Timestamp getOrderDate() {
        return this.orderDate;
    }
    
    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="order")
    public Set<OrderHasProducts> getOrderHasProductses() {
        return this.orderHasProductses;
    }
    
    public void setOrderHasProductses(Set<OrderHasProducts> orderHasProductses) {
        this.orderHasProductses = orderHasProductses;
    }




}



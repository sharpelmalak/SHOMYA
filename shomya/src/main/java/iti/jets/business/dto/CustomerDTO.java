package iti.jets.business.dto;

import java.sql.Date;
import java.util.List;


public class CustomerDTO {
    private int id;
    private String name;
    private String username;
    private String password;
    private String email;
    private Date birthdate;
    private String job;
    private float creditLimit;
    private String address;
    private List<Integer> ordersIds;
    private List<Integer> interestsIds;
    private List<Integer> wishListIds;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public float getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(float creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Integer> getOrdersIds() {
        return ordersIds;
    }

    public void setOrdersIds(List<Integer> ordersIds) {
        this.ordersIds = ordersIds;
    }

    public List<Integer> getInterestsIds() {
        return interestsIds;
    }

    public void setInterestsIds(List<Integer> interestsIds) {
        this.interestsIds = interestsIds;
    }

    public List<Integer> getWishListIds() {
        return wishListIds;
    }

    public void setWishListIds(List<Integer> wishListIds) {
        this.wishListIds = wishListIds;
    }
}

package iti.jets.dao;

import iti.jets.model.Product;
import iti.jets.model.User;

import java.util.List;

public class UserDAO extends DAO<User,Integer>{
    public UserDAO() {
        super();
    }

    @Override
    public List<User> search(User criteria) {
        return List.of();
    }
}

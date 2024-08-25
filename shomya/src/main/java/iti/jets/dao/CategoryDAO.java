package iti.jets.dao;

import iti.jets.model.Category;
import java.util.List;

public class CategoryDAO extends DAO<Category,Integer> {


    public CategoryDAO() {
        super();
    }

    @Override
    public List<Category> search(Category criteria) {
        return List.of();
    }
}

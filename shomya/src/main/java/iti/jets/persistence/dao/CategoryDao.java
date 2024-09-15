package iti.jets.persistence.dao;

import iti.jets.business.dto.CategoryDTO;
import iti.jets.business.service.helper.CategoryMapper;
import iti.jets.persistence.model.Category;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends DAO<Category,Integer>{
    public CategoryDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Category> search(Category criteria) {
        return List.of();
    }


    public List<CategoryDTO> findAllCategories() {
        List<Category> categories = super.findAll();
        List<CategoryDTO> categoriesDTO = new ArrayList<>();
        for (Category category : categories) {
            categoriesDTO.add(CategoryMapper.toDTO(category));
        }
        return categoriesDTO;
    }

}

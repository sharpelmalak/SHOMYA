package iti.jets.business.service;

import iti.jets.business.dto.CategoryDTO;
import iti.jets.business.service.helper.CategoryMapper;
import iti.jets.persistence.dao.CategoryDao;
import iti.jets.persistence.util.ConnectionInstance;

import java.util.List;

public class CategoryService {

    public static List<CategoryDTO> getAllCategories(ConnectionInstance connection) {
        CategoryDao categoryDao = new CategoryDao(connection.getEntityManager());
        return  categoryDao.findAllCategories();
    }
    public static boolean addCategory(ConnectionInstance connection,String name,byte[] image) {
        try{
            CategoryDao categoryDao = new CategoryDao(connection.getEntityManager());
            CategoryDTO dto = new CategoryDTO();
            dto.setName(name);
            dto.setImage(image);
            categoryDao.save(CategoryMapper.toEntity(dto));
            return true;
        }
        catch(Exception e){
            return false;
        }

    }
}

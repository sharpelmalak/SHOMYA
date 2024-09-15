package iti.jets.business.service.helper;

import iti.jets.business.dto.CategoryDTO;
import iti.jets.persistence.model.Category;
import iti.jets.persistence.model.Product;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CategoryMapper {

    // Convert Category to CategoryDTO
    public static CategoryDTO toDTO(Category category) {
        if (category == null) {
            return null;
        }

        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setImage(category.getImage());

        // Convert Set<Product> to List<Long>
        List<Integer> productIds = category.getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList());

        dto.setProductsIds(productIds);
        return dto;
    }

    // Convert CategoryDTO to Category
    public static Category toEntity(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }

        Category category = new Category();
        category.setName(dto.getName());
        category.setImage(dto.getImage());
        return category;
    }

    // Simulated method to fetch Product by ID (replace with actual repository call)
//    private Product fetchProductById(int id) {
//        // Example implementation; replace with your actual data fetching logic
//        Product product = new Product();
//        product.setId(id);
//        return product;
//    }
}


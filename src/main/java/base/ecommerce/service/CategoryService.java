package base.ecommerce.service;

import base.ecommerce.payload.CategoryDTO;
import base.ecommerce.payload.CategoryResponse;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
    CategoryDTO deleteCategory(Long categoryId);
}

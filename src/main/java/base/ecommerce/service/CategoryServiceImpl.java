package base.ecommerce.service;

import base.ecommerce.exceptions.APIException;
import base.ecommerce.exceptions.ResourceNotFoundException;
import base.ecommerce.model.Category;
import base.ecommerce.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList.isEmpty()) {
            throw new APIException("No category found.");
        }
        return categoryList;
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepository.findCategoryByCategoryName(category.getCategoryName());
        if (savedCategory != null) {
            throw new APIException("Category: " + category.getCategoryName() + " already existed.");
        }
        categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> savedCategoryOptional = categoryRepository.findById(categoryId);
        Category savedCategory = savedCategoryOptional
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);
        return savedCategory;
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        Category category = categoryOptional
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        categoryRepository.delete(category);
        return "Deleted Category with ID: " + categoryId;
    }
}

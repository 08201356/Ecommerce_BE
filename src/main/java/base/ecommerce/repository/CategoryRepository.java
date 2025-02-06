package base.ecommerce.repository;

import base.ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByCategoryName(String categoryName);
}

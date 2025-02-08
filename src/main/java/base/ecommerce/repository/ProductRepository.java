package base.ecommerce.repository;

import base.ecommerce.model.Category;
import base.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByCategoryOrderByPriceAsc(Category category);
    List<Product> findProductsByProductNameLikeIgnoreCase(String keyword);
}

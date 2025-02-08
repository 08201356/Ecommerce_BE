package base.ecommerce.repository;

import base.ecommerce.model.Category;
import base.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findProductsByCategoryOrderByPriceAsc(Category category, Pageable pageDetails);
    Page<Product> findProductsByProductNameLikeIgnoreCase(String keyword, Pageable pageDetails);
}

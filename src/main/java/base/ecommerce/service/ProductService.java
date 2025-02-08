package base.ecommerce.service;

import base.ecommerce.model.Product;
import base.ecommerce.payload.ProductDTO;
import base.ecommerce.payload.ProductResponse;

public interface ProductService {
    ProductResponse getAllProducts();
    ProductResponse getProductsByCategory(Long categoryId);
    ProductResponse searchProductsByKeyword(String keyword);
    ProductDTO addProduct(Long categoryId, Product product);
    ProductDTO updateProduct(Product product, Long productId);
    ProductDTO deleteProduct(Long productId);
}

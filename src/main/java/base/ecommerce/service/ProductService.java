package base.ecommerce.service;

import base.ecommerce.model.Product;
import base.ecommerce.payload.ProductDTO;
import base.ecommerce.payload.ProductResponse;

public interface ProductService {
    ProductResponse getAllProducts();
    ProductDTO addProduct(Long categoryId, Product product);
}

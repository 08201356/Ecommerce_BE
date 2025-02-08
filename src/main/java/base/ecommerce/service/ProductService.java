package base.ecommerce.service;

import base.ecommerce.payload.ProductDTO;
import base.ecommerce.payload.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {
    ProductResponse getAllProducts();
    ProductResponse getProductsByCategory(Long categoryId);
    ProductResponse searchProductsByKeyword(String keyword);
    ProductDTO addProduct(Long categoryId, ProductDTO productDTO);
    ProductDTO updateProduct(ProductDTO productDTO, Long productId);
    ProductDTO updateProductImage(Long productId, MultipartFile image)  throws IOException;
    ProductDTO deleteProduct(Long productId);
}

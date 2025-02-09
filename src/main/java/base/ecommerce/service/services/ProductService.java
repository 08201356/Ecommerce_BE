package base.ecommerce.service.services;

import base.ecommerce.payload.entity_dto.ProductDTO;
import base.ecommerce.payload.entity_response.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {
    ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    ProductResponse getProductsByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    ProductResponse searchProductsByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    ProductDTO addProduct(Long categoryId, ProductDTO productDTO);
    ProductDTO updateProduct(ProductDTO productDTO, Long productId);
    ProductDTO updateProductImage(Long productId, MultipartFile image)  throws IOException;
    ProductDTO deleteProduct(Long productId);
}

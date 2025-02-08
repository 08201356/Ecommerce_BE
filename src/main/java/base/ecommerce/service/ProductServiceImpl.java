package base.ecommerce.service;

import base.ecommerce.exceptions.ResourceNotFoundException;
import base.ecommerce.model.Category;
import base.ecommerce.model.Product;
import base.ecommerce.payload.ProductDTO;
import base.ecommerce.payload.ProductResponse;
import base.ecommerce.repository.CategoryRepository;
import base.ecommerce.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductResponse getAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = productList.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOList);
        return productResponse;
    }

    @Override
    public ProductResponse getProductsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        List<Product> productList = productRepository.findProductsByCategoryOrderByPriceAsc(category);
        List<ProductDTO> productDTOList = productList.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOList);
        return productResponse;
    }

    @Override
    public ProductResponse searchProductsByKeyword(String keyword) {
        List<Product> productList = productRepository.findProductsByProductNameLikeIgnoreCase('%' + keyword + '%');
        List<ProductDTO> productDTOList = productList.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOList);
        return productResponse;
    }

    @Override
    public ProductDTO addProduct(Long categoryId, Product product) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        product.setCategory(category);
        product.setImage("default.png");
        double specialPrice = product.getPrice() - ((product.getDiscount() * 0.01) * product.getPrice());
        product.setSpecialPrice(specialPrice);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(Product product, Long productId) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        existingProduct.setProductName(product.getProductName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDiscount(product.getDiscount());
        double specialPrice = existingProduct.getPrice() - ((existingProduct.getDiscount() * 0.01) * existingProduct.getPrice());
        existingProduct.setSpecialPrice(specialPrice);

        Product savedProduct = productRepository.save(existingProduct);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO deleteProduct(Long productId) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        productRepository.delete(existingProduct);
        return modelMapper.map(existingProduct, ProductDTO.class);
    }
}

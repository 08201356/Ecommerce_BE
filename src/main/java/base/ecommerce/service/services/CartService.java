package base.ecommerce.service.services;

import base.ecommerce.payload.entity_dto.CartDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CartService {
    List<CartDTO> getAllCarts();
    CartDTO getCart(String emailId, Long cartId);
    CartDTO addProductToCart(Long productId, Integer quantity);

    @Transactional
    CartDTO updateProductQuantityInCart(Long productId, Integer quantity);
    String deleteProductFromCart(Long cartId, Long productId);
    void updateProductInCarts(Long cartId, Long productId);
}

package base.ecommerce.service.services;

import base.ecommerce.payload.entity_dto.CartDTO;

public interface CartService {
    CartDTO addProductToCart(Long productId, Integer quantity);
}

package base.ecommerce.service.services_impl;

import base.ecommerce.exceptions.APIException;
import base.ecommerce.exceptions.ResourceNotFoundException;
import base.ecommerce.model.Cart;
import base.ecommerce.model.CartItem;
import base.ecommerce.model.Product;
import base.ecommerce.payload.entity_dto.CartDTO;
import base.ecommerce.payload.entity_dto.ProductDTO;
import base.ecommerce.repository.CartItemRepository;
import base.ecommerce.repository.CartRepository;
import base.ecommerce.repository.ProductRepository;
import base.ecommerce.service.services.CartService;
import base.ecommerce.util.AuthUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AuthUtil authUtil;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CartDTO addProductToCart(Long productId, Integer quantity) {
        //Create new cart
        Cart cart = createCart();

        //Check if product exists
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        //Check if cart item exists, and the quantity suffices
        CartItem cartItem = cartItemRepository.findCartItemByProductIdAndCartId(cart.getCartId(), productId);
        if (cartItem != null) {
            throw new APIException("Product " + product.getProductName() + " already existed in the cart.");
        }
        if (product.getQuantity() == 0) {
            throw new APIException("Product " + product.getProductName() + " is not available");
        }
        if (product.getQuantity() < quantity) {
            throw  new APIException("Product " + product.getProductName() + " does not have the ordered amount.");
        }

        //Update cart item
        CartItem newCartItem = new CartItem();
        newCartItem.setProduct(product);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(quantity);
        newCartItem.setDiscount(product.getDiscount());
        newCartItem.setProductPrice(product.getSpecialPrice());
        cartItemRepository.save(newCartItem);

        //Update cart
        cart.setTotalPrice(cart.getTotalPrice() + (product.getSpecialPrice() * quantity));
        cartRepository.save(cart);

        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

        List<CartItem> cartItemList = cart.getCartItemList();

        Stream<ProductDTO> productDTOStream = cartItemList.stream()
                .map(item -> {
                    ProductDTO map = modelMapper.map(item.getProduct(), ProductDTO.class);
                    map.setQuantity(item.getQuantity());
                    return map;
                });

        cartDTO.setProductDTOList(productDTOStream.toList());
        return cartDTO;
    }

    private Cart createCart() {
        Cart userCart = cartRepository.findCartByEmail(authUtil.loggedInEmail());
        if (userCart != null) {
            return userCart;
        }

        Cart cart = new Cart();
        cart.setTotalPrice(0.0);
        cart.setUser(authUtil.loggedInUser());
        Cart newCart = cartRepository.save(cart);

        return newCart;
    }
}

package base.ecommerce.repository;

import base.ecommerce.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("select ci from CartItem ci where ci.cart.cartId = ?1 and ci.product.productId = ?2")
    CartItem findCartItemByProductIdAndCartId(Long cartId, Long productId);

    @Modifying
    @Query("delete from CartItem ci where ci.cart.cartId = ?1 and ci.product.productId = ?2")
    void deleteCartItemByProductIdAndCartId(Long cartId, Long productId);
}

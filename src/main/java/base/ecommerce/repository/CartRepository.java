package base.ecommerce.repository;

import base.ecommerce.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("select c from Cart c where c.user.email = ?1")
    Cart findCartByEmail(String email);

    @Query("select c from Cart c where c.user.email = ?1 and c.cartId = ?2")
    Cart findCartByEmailAndCartId(String email, Long cartId);

    @Query("select c from Cart c join fetch c.cartItemList ci join fetch ci.product p where p.productId = ?1")
    List<Cart> findCartsByProductId(Long productId);
}

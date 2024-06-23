package com.Ravicomputer.ecommerce.Repository;

import com.Ravicomputer.ecommerce.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByCartId(long id);
    Cart findByUserId(long id);
}

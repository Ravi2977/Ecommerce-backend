package com.Ravicomputer.ecommerce.Repository;

import com.Ravicomputer.ecommerce.Model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderModel,Long> {
    List<OrderModel> findByUserId(long id);
}

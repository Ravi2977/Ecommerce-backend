package com.Ravicomputer.ecommerce.Repository;

import com.Ravicomputer.ecommerce.Model.OrdersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<OrdersModel,Long> {
}

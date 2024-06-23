package com.Ravicomputer.ecommerce.Repository;

import com.Ravicomputer.ecommerce.Model.ProductReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductReviewRepository extends JpaRepository<ProductReviewModel,Long> {
    List<ProductReviewModel> findByProduct_ProductId(long productId);
}

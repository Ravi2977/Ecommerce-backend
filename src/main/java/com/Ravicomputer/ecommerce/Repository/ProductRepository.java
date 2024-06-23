package com.Ravicomputer.ecommerce.Repository;

import com.Ravicomputer.ecommerce.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p WHERE p.category.categoryId =?1")
    List<Product> findByCategory_categoryId(long categoryId);
    Product findByProductId(long id);

}

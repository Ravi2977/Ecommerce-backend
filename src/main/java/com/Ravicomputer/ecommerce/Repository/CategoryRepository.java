package com.Ravicomputer.ecommerce.Repository;

import com.Ravicomputer.ecommerce.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCategoryName(String categoryName);
}

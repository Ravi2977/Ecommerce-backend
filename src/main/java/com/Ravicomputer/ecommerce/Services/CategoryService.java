package com.Ravicomputer.ecommerce.Services;

import com.Ravicomputer.ecommerce.Model.Category;
import com.Ravicomputer.ecommerce.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
@Autowired
    private CategoryRepository categoryRepository;

public Category getByName(String name){
    return categoryRepository.findByCategoryName(name);
}

}

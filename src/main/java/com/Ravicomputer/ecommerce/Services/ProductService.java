package com.Ravicomputer.ecommerce.Services;

import com.Ravicomputer.ecommerce.Model.Product;
import com.Ravicomputer.ecommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;



    public ResponseEntity<Product> addProduct(Product product){
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }
    public List<Product> productByCategoryId(long id){
        return productRepository.findByCategory_categoryId(id);
    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product getById(long id){
        return productRepository.findByProductId(id);
    }
}

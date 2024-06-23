package com.Ravicomputer.ecommerce.Services;

import com.Ravicomputer.ecommerce.Model.ProductReviewModel;
import com.Ravicomputer.ecommerce.Repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductReviewService {
    @Autowired
    private ProductReviewRepository productReviewRepository;

    public ResponseEntity<ProductReviewModel> addProductReview(ProductReviewModel productReviewModel){
        return new ResponseEntity<>(productReviewRepository.save(productReviewModel), HttpStatus.CREATED);
    }
    public List<ProductReviewModel> getByProductId(long id){
        return productReviewRepository.findByProduct_ProductId(id);
    }
}

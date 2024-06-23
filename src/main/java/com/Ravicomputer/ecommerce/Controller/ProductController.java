package com.Ravicomputer.ecommerce.Controller;

import com.Ravicomputer.ecommerce.Model.Category;
import com.Ravicomputer.ecommerce.Model.Product;
import com.Ravicomputer.ecommerce.Model.ProductReviewModel;
import com.Ravicomputer.ecommerce.Repository.CategoryRepository;
import com.Ravicomputer.ecommerce.Services.ProductReviewService;
import com.Ravicomputer.ecommerce.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = {"http://localhost:3000","https://ravi-computer-ecommerce.vercel.app"})
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductReviewService productReviewService;
    @Autowired
    private CategoryRepository categoryRepository;


    @PostMapping("/productReview")
    public ResponseEntity<ProductReviewModel> addProductReview(@RequestBody ProductReviewModel productReviewModel){
        return productReviewService.addProductReview(productReviewModel);
    }
    @PostMapping("addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.CREATED);
    }
    @PostMapping("addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
}

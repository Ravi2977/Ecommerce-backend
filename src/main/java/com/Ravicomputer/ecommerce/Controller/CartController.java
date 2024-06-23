package com.Ravicomputer.ecommerce.Controller;

import com.Ravicomputer.ecommerce.Model.*;
import com.Ravicomputer.ecommerce.Services.CartService;
import com.Ravicomputer.ecommerce.Services.ProductService;
import com.Ravicomputer.ecommerce.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = {"http://localhost:3000","https://ravi-computer-ecommerce.vercel.app"})
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;


    @PostMapping("/updateCart")
    public Cart updateProductInCart(@RequestBody UpdateProductRequestModel model){
        Product product = productService.getById(model.getProductId());
        return cartService.updateCartProducts(model.getUserId(),product);
    }
    @GetMapping("/byUserId/{userId}")
    public Cart cartByUserId(@PathVariable long userId){
        System.out.println("CLicked for this end point");
        Cart  cart =cartService.getCartBYUSerID(userId);
        for(int i=0;i<cart.getProducts().size();i++){
            cart.getProducts().get(i).setProductReview(null);
        }
        return cart;
    }
    @PostMapping("/removeOneQuantity")
        public int removeOneProductFromCart(@RequestBody RemoveByOneRequestModel model){
           return cartService.removeProductByUserIdAndProductId(model.getUserId(),model.getProductId()).getProducts().size();
        }

}

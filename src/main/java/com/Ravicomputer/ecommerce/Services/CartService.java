package com.Ravicomputer.ecommerce.Services;

import com.Ravicomputer.ecommerce.Model.Cart;
import com.Ravicomputer.ecommerce.Model.Product;
import com.Ravicomputer.ecommerce.Model.UserModel;
import com.Ravicomputer.ecommerce.Repository.CartRepository;
import com.Ravicomputer.ecommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

public Cart createCart(Cart cart){
    return cartRepository.save(cart);
}

    public Cart updateCartProducts(long userId, Product product){
        Cart cart = cartRepository.findByUserId(userId);
        cart.getProducts().add(product);
        cartRepository.save(cart);
        return cartRepository.findByUserId(userId);

    }
    public Cart getCartBYUSerID(long id){
      return   cartRepository.findByUserId(id);
    }

    public Cart removeProductByUserIdAndProductId(long userId, long productId) {
        Cart existingCart = cartRepository.findByUserId(userId);
        Product existingProduct=productRepository.findByProductId(productId);
        List<Product> productList = existingCart.getProducts();
        for(int i=0;i<productList.size();i++){
          if(productList.get(i).getProductId()==existingProduct.getProductId()){
              Product removed = productList.remove(i);
              System.out.println(removed);
              System.out.println(productList.size());
              break;
          }
        }
        System.out.println(productList.indexOf(existingProduct));

        return cartRepository.save(existingCart);
    }

}

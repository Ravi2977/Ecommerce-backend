package com.Ravicomputer.ecommerce.Controller;

import com.Ravicomputer.ecommerce.Model.*;
import com.Ravicomputer.ecommerce.Repository.CartRepository;
import com.Ravicomputer.ecommerce.Repository.CategoryRepository;
import com.Ravicomputer.ecommerce.Repository.UserRepository;
import com.Ravicomputer.ecommerce.Security.JwtHelper;
import com.Ravicomputer.ecommerce.Services.ProductReviewService;
import com.Ravicomputer.ecommerce.Services.ProductService;
import com.Ravicomputer.ecommerce.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(value = {"http://localhost:3000","https://ravi-computer-ecommerce.vercel.app"})
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;
@Autowired
private PasswordEncoder passwordEncoder;
@Autowired
private JwtHelper jwtHelper;
@Autowired
private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductReviewService productReviewService;
    @Autowired
    private CategoryRepository categoryRepository;
@Autowired
private ProductService productService;
@Autowired
private CartRepository cartRepository;
    @PostMapping("/signup")
    public UserModel signup(@RequestBody UserModel user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setVerificationStatus(false);
        userService.signUp(user);
        UserModel addedUSer = userService.getByEmail(user.getEmail());
        Cart newCart = new Cart();
        newCart.setUser(new UserModel());
        newCart.getUser().setId(addedUSer.getId());
        cartRepository.save(newCart);
//        user.setRole("ADMIN");
        return addedUSer;
    }
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest){
        this.doAuthenticate(jwtRequest.getEmail(),jwtRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
        String token =this.jwtHelper.generateToken(userDetails);
        JwtResponse response = JwtResponse.builder().jwtToken(token).userNAme(userDetails.getUsername()).build();
        UserModel user=userService.getByEmail(jwtRequest.getEmail());
        response.setName(user.getName());
        response.setVerified(user.getVerificationStatus());
        response.setUserId(user.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/users")
    public List<UserModel> getUsers(){
        List<UserModel> users =userRepository.findAll();
        for(int i=0;i<users.size();i++){
            users.get(i).setOrders(null);
        }
     return users;
    }
    private void doAuthenticate(String email,String password){
        UsernamePasswordAuthenticationToken authentication =new UsernamePasswordAuthenticationToken(email,password);
        try{
            authenticationManager.authenticate(authentication);
        }catch(BadCredentialsException be){
//          System.out.println(be);
            throw new BadCredentialsException("Invalid User name of Password");
        }
    }
    @GetMapping("/productByCategory/{id}")
    public List<Product> productByCategory(@PathVariable long id){
        return productService.productByCategoryId(id);
    }
    @GetMapping("/productReviewByProductId/{id}")
    public List<ProductReviewModel> getAllReviewOfProduct(@PathVariable  long id){

        return productReviewService.getByProductId(id);
    }
    @GetMapping("/getAllCategory")
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    @GetMapping("/allProducts")
    public List<Product> getAllProducts(){
        List<Product> products =productService.getAllProducts();
        for(int i=0;i<products.size();i++){
            products.get(i).setProductReview(null);
        }
        return products;
    }
    @PostMapping("addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody ProductAddRequestModel product){
        Product pro = new Product();
        pro.setProductName(product.getProductName());
        pro.setProductDesc(product.getProductDesc());
        pro.setBrand(product.getBrand());
        pro.setKeyFeature(product.getKeyFeature());
        pro.setActualPrice(product.getActualPrice());
        pro.setProductMrp(product.getProductMrp());
        pro.setProductImgUrl(product.getProductImgUrl());
        pro.setCategory(new Category());
        pro.getCategory().setCategoryId(categoryRepository.findByCategoryName(product.getCategory()).getCategoryId());
        pro.setAddedOnDate(LocalDate.now());
        pro.setLastUpdate(LocalDate.now());
        pro.setDiscountPercentage(((float) (product.getProductMrp() - product.getActualPrice()) / product.getProductMrp()) * 100);
        pro.setProductImgUrl2(product.getProductImgUrl2());
        pro.setProductImgUrl3(product.getProductImgUrl3());
        return productService.addProduct(pro);
    }
    @GetMapping("/getCategoryByName/{name}")
    public long getByName(@PathVariable  String name){
        return categoryRepository.findByCategoryName(name).getCategoryId();
    }
    @GetMapping("/getProductByProductId/{id}")
    public Product getProductByProductId(@PathVariable long id){
        Product product=productService.getById(id);
        for(int i=0;i<product.getProductReview().size();i++){
            product.getProductReview().get(i).getUser().setProductReviews(null);
            product.getProductReview().get(i).getUser().setCart(null);
            product.getProductReview().get(i).getUser().setOrders(null);
        }
        return  product;
    }
    @GetMapping("/cart")
    public List<Cart> getAllCart(){
        return cartRepository.findAll();
    }
    @GetMapping("/userByMail/{email}")
    public UserModel userByMail(@PathVariable String email){
        return userRepository.findByEmail(email);
    }

    @GetMapping("/productByCategoryId/{id}")
    public List<Product> productByCategoryId(@PathVariable long id){
      List<Product> products = productService.productByCategoryId(id);
      for(int i=0;i<products.size();i++){
          products.get(i).setProductReview(null);
      }
       return products;
    }
    @GetMapping("/getProductById/{id}")
    public Product getProductById(@PathVariable long id){
        Product product= productService.getById(id);
      for(int i=0;i<  product.getProductReview().size();i++){
          product.getProductReview().get(i).setUser(null);
      }
        return  product;
    }
    @GetMapping("/getUserById/{id}")
    public  UserModel getUserById(@PathVariable long id){
        UserModel user = userRepository.findById(id);
        for(int i=0;i<user.getOrders().size();i++){
            user.getOrders().get(i).setBillingAddress(null);
            user.getOrders().get(i).setOrderDate(null);
            user.getOrders().get(i).setExpectedDeliveryDate(null);
            user.getOrders().get(i).setTaxes(0);
            user.getOrders().get(i).setProducts(null);
            user.getOrders().get(i).setUser(null);
        }
        return user;

    }
    @GetMapping("/setVerify/{email}")
    public String steVerify(@PathVariable String email){
        UserModel userModel=userService.getByEmail(email);
        userModel.setVerificationStatus(true);
        userRepository.save(userModel);
        return "You are Verified now you can Login";
    }
    @PostMapping("/change")
    public String changePassword(@RequestBody Map<String,String> model){
        UserModel userModel = userRepository.findById(Integer.parseInt(model.get("userId")));
        if(passwordEncoder.matches(model.get("oldPassowrd"),userModel.getPassword())){
            userModel.setPassword(passwordEncoder.encode(model.get("newPassword")));
            userRepository.save(userModel);
            return "Password Updated";
        }else{
            return "Old password is wrong";
        }

    }
}

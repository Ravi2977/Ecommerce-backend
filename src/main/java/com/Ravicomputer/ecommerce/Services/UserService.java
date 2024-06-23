package com.Ravicomputer.ecommerce.Services;

import com.Ravicomputer.ecommerce.Model.UserModel;
import com.Ravicomputer.ecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;

    public ResponseEntity<UserModel> signUp(UserModel user){
        String text="Hi"+user.getName()+" "+" this email only for verification \n"+"Verify your email by clicking this link bellow\n"+"this is your email"+user.getEmail()+"this is your verification link \n"+"https//verificationravicomputer.com";
        emailService.sendEmail(user.getEmail(),"Verification mail",text);
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }
    public UserModel getByEmail(String email){
        return userRepository.findByEmail(email);
    }
}

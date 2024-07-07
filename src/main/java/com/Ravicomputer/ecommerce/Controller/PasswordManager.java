package com.Ravicomputer.ecommerce.Controller;

import com.Ravicomputer.ecommerce.Model.UserModel;
import com.Ravicomputer.ecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/password")
public class PasswordManager {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/change")
    public String changePassword(@RequestBody Map<String,String> model){
        UserModel userModel = userRepository.findById(Integer.parseInt(model.get("userId")));
        if(passwordEncoder.matches(model.get("oldPassowrd"),userModel.getPassword())){
            userModel.setPassword(model.get("newPassword"));
            return "Password Updated";
        }else{
            return "Old password is wrong";
        }

    }
}

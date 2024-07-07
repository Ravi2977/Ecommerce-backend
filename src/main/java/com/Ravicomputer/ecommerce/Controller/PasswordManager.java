package com.Ravicomputer.ecommerce.Controller;

import com.Ravicomputer.ecommerce.Model.UserModel;
import com.Ravicomputer.ecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://ecommerce-backend-bmf8.onrender.com", "https://ravi-computer-ecommerce.vercel.app"})
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
            userModel.setPassword(passwordEncoder.encode(model.get("newPassword")));
            userRepository.save(userModel);
            return "Password Updated";
        }else{
            return "Old password is wrong";
        }

    }
}

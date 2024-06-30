package com.Ravicomputer.ecommerce.Controller;

import com.Ravicomputer.ecommerce.Model.UserAddressModel;
import com.Ravicomputer.ecommerce.Repository.AddressRepository;
import com.Ravicomputer.ecommerce.Services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = {"http://localhost:3000","https://ravi-computer-ecommerce.vercel.app"})
@RequestMapping("/address")
public class AddressCOntroller {
    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressRepository addressRepository;

    @PostMapping("/addAddress")
    public UserAddressModel addAddress(@RequestBody UserAddressModel address){
        return addressService.addAddress(address);
    }

    @GetMapping("/getAddressByUserId/{id}")
    public UserAddressModel getByUSerID(@PathVariable long id){
        UserAddressModel address = addressService.getByUserId(id);
        address.setUser(null);
        return address;
    }

}

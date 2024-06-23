package com.Ravicomputer.ecommerce.Services;

import com.Ravicomputer.ecommerce.Model.UserAddressModel;
import com.Ravicomputer.ecommerce.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;


    public UserAddressModel addAddress(UserAddressModel address){
        return addressRepository.save(address);
    }
    public  UserAddressModel getByUserId(long id){
        return addressRepository.findByUser_id(id);
    }
}

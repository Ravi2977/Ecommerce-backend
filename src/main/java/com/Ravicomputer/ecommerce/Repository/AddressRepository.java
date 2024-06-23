package com.Ravicomputer.ecommerce.Repository;

import com.Ravicomputer.ecommerce.Model.UserAddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<UserAddressModel,Long> {
    UserAddressModel findByUser_id(long id);
}

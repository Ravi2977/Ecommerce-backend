package com.Ravicomputer.ecommerce.Repository;

import com.Ravicomputer.ecommerce.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel,Long> {
    UserModel findByEmail(String email);
    UserModel findById(long id);
}

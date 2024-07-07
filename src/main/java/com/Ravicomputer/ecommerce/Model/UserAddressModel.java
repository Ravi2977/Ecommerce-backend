package com.Ravicomputer.ecommerce.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class UserAddressModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long addressId;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String mobileNumber;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String district;
    @Column(nullable = false)
    private String city;
    private String locality;
    private String landMark;
    @Column(nullable = false)
    private int pinCode;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;


}

package com.Ravicomputer.ecommerce.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;
    @ManyToMany
    private List<Product> products;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
    private long totalAmount;
    private long netPayableAmount;
    private int totalQuantity;
    private String orderStatus;
    private String paymentStatus;
    private LocalDate orderDate;
    private LocalDate expectedDeliveryDate;
    private float taxes;
    private int deliveryCharge;
    @ManyToOne
    @JoinColumn(name = "billing_address_id")
    private UserAddressModel billingAddress;



}

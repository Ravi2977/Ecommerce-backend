package com.Ravicomputer.ecommerce.Model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAddRequestModel {
    private int productMrp;
    private int actualPrice;
    private String category;
    private String keyFeature;
    private String brand;
    private String productName;
    private String productImgUrl;
    private String productImgUrl2;
    private String productImgUrl3;
    private String productDesc;
}

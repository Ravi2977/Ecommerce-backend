package com.Ravicomputer.ecommerce.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;
    @Column(unique = true, nullable = false)
    private String productName;
    @Column(nullable = false)
    private String productImgUrl;
    private String productImgUrl2;
    private String productImgUrl3;
    private String productDesc;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    private int productMrp;
    private int actualPrice;
    private float discountPercentage;
    private String keyFeature;
    private String brand;
    private LocalDate addedOnDate;
    private LocalDate lastUpdate;
    @OneToMany(mappedBy = "product")
    private List<ProductReviewModel> productReview;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

}

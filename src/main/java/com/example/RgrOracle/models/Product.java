package com.example.RgrOracle.models;

import jakarta.persistence.*;

@Entity
@Table(name = "t_products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "user_role")
    private int productPrice;
    public Product(){

    }
    public Product(String ProductName, String ProductDescription, int ProductPrice) {
        this.productName = ProductName;
        this.productDescription = ProductDescription;
        this.productPrice = ProductPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getId() {
        return id;
    }
}

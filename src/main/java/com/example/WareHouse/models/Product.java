package com.example.WareHouse.models;

import jakarta.persistence.*;

@Entity
@Table(name = "t_products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String productName;
    @Column
    private String productDescription;
    @Column
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

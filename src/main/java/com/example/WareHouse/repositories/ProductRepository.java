package com.example.WareHouse.repositories;

import com.example.WareHouse.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByProductName(String productName);
}

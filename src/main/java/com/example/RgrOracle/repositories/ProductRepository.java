package com.example.RgrOracle.repositories;

import com.example.RgrOracle.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByProductName(String productName);
}

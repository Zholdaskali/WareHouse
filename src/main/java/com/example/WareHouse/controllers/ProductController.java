package com.example.WareHouse.controllers;

import com.example.WareHouse.models.Product;
import com.example.WareHouse.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    private List<Product> products = new ArrayList<>();

    @GetMapping("/")
    public String main(Map<String, Iterable<Product>> model) {
        Iterable<Product> products = productRepository.findAll();
        model.put("products", products);
        return "main";
    }

    @PostMapping("/add")
    public String add(
            @RequestParam String productName,
            @RequestParam String productDescription,
            @RequestParam int productPrice,
            Map<String, Object> model)
    {
        if (productName.isBlank() || productDescription.isBlank() || productPrice <= 0) {
            return "redirect:/";
        }

        Product product = new Product(productName, productDescription, productPrice);
        productRepository.save(product);

        Iterable<Product> products = productRepository.findAll();
        model.put("products", products);

        return "redirect:/";
    }

    @PostMapping("/filter")
    public String filter(
            @RequestParam String productName,
            Map<String, Object> model)
    {
        Iterable<Product> products;
        products = productRepository.findAll();
        if (productName != null && !productName.isEmpty()) {
            products = productRepository.findByProductName(productName);
        } else {
            return "redirect:/";
        }
        model.put("products", products);
        return "main";
    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestParam Long productId) {
        productRepository.deleteById(productId);
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String editProduct(@RequestParam("productId") Long productId,
                              @RequestParam("productName") String productName,
                              @RequestParam("productDescription") String productDescription,
                              @RequestParam("productPrice") int productPrice) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product productToUpdate = optionalProduct.get();
            productToUpdate.setProductName(productName);
            productToUpdate.setProductDescription(productDescription);
            productToUpdate.setProductPrice(productPrice);
            productRepository.save(productToUpdate);
        }
        return "redirect:/";
    }
}

package com.example.RgrOracle.controllers;

import com.example.RgrOracle.models.Product;
import com.example.RgrOracle.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public String editProduct(@RequestParam("productId") int productId,
                              @RequestParam("productName") String productName,
                              @RequestParam("productDescription") String productDescription,
                              @RequestParam("productPrice") int productPrice) {
        // Находим продукт в списке по идентификатору
        Product productToUpdate = null;
        for (Product product : products) {
            if (product.getId() == productId) {
                productToUpdate = product;
                break;
            }
        }

        // Если продукт найден, обновляем его данные
        if (productToUpdate != null) {
            productToUpdate.setProductName(productName);
            productToUpdate.setProductDescription(productDescription);
            productToUpdate.setProductPrice(productPrice);
        }

        return "redirect:/products";
    }
}

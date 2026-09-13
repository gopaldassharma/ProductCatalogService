package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @GetMapping("/products")
    public List<Product> getProducts(){
        return null;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return null;
    }

    @PostMapping("/products/create")
    public Product createProduct(@RequestBody Product product){
        return product;
    }



}

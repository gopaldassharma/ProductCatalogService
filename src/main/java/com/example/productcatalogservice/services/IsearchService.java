package com.example.productcatalogservice.services;

import com.example.productcatalogservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IsearchService {
    public List<Product> searchProducts(String query, Integer pageNumber, Integer pageSize);
}

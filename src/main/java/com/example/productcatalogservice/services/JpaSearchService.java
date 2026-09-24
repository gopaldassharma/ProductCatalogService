package com.example.productcatalogservice.services;

import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JpaSearchService implements IsearchService{
    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> searchProducts(String query, Integer pageNumber, Integer pageSize) {
        return productRepo.findProductByName(query, PageRequest.of(pageNumber, pageSize));
    }

}

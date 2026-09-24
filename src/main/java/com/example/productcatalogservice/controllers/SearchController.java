package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.CategoryDto;
import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.dtos.SearchRequestDto;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/search")
@RestController
public class SearchController {

    @Autowired
    private IsearchService searchService;

    @PostMapping
    public ResponseEntity<List<ProductDto>> searchProduct(@RequestBody SearchRequestDto searchRequestDto){
        List<Product> products = searchService.searchProducts(searchRequestDto.getQuery(), searchRequestDto.getPageNumber(), searchRequestDto.getPageLimit());
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products){
            productDtos.add(getProductDto(product));
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    public ProductDto getProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setId(product.getId());
        if(product.getCategory() != null){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setDescription(product.getCategory().getDescription());
            categoryDto.setId(product.getCategory().getId());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }
}

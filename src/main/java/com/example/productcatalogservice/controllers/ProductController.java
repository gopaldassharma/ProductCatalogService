package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.CategoryDto;
import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import static org.springframework.beans.BeanUtils.copyProperties;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    IProductService productService;

    @GetMapping
    public List<ProductDto> getProducts(){
        List<ProductDto> response = new ArrayList<>();
        List<Product> products = productService.getAllProducts();
        for(Product product: products){
            response.add(getProductDto(product));

        }
        return response;
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id){
            if (id <= 0) {
                throw new IllegalArgumentException("Product Id is Invalid");
            }
            Product product = productService.getProductById(id);
            ProductDto productDto = new ProductDto();
            CategoryDto categoryDto = new CategoryDto();
            if (product != null) {
                copyProperties(product.getCategory(), categoryDto);
                copyProperties(product, productDto);
                productDto.setCategory(categoryDto);
            }
            return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto product){
        return product;
    }

    @PutMapping("{id}")
    public ProductDto replaceProduct(@PathVariable("id") Long Id, @RequestBody ProductDto productDto){
        Product input = getProduct(productDto);
        Product product = productService.replaceProduct(input, Id);
        return getProductDto(product);
    }

    public Product getProduct(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setId(productDto.getId());
        if(productDto.getCategory() != null){
            Category category = new Category();
            category.setName(productDto.getCategory().getName());
            category.setDescription(productDto.getCategory().getDescription());
            category.setId(productDto.getCategory().getId());
            product.setCategory(category);
        }
        return product;
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
    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    public ResponseEntity<String> handleException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


}

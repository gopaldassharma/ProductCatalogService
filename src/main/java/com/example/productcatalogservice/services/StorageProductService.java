package com.example.productcatalogservice.services;

import com.example.productcatalogservice.dtos.UserDto;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepo.findById(id);
        if(product.isPresent()){
            return product.get();
        }
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return  productRepo.findAll();
    }

    @Override
    public Product replaceProduct(Product product, Long id) {
        return null;
    }

    @Override
    public Product getProductBasedOnUserRole(Long userId, Long productId) {
        Product product = productRepo.findById(productId).get();
        UserDto userDto = restTemplate.getForEntity("http://localhost:9000/user/{userId}", UserDto.class,userId).getBody();
        if(userDto!=null){
            System.out.println("recieved User");
            return product;
        }
        return null;
    }
}

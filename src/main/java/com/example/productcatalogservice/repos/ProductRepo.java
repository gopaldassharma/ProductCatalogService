package com.example.productcatalogservice.repos;

import com.example.productcatalogservice.models.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
   Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findProductByPriceBetween(Double priceStart, Double priceEnd);
    List<Product> findAllByIsPrimeTrue();
    List<Product> findAllByOrderByPriceDesc();
    List<Product> findAllByOrderByPriceAsc();
    List<Product> findProductByName(String query, Pageable pageable);
    // Native query

//    @Query("Select p.name  ")
//    String findProductNameFromProductId(Long productId);

}

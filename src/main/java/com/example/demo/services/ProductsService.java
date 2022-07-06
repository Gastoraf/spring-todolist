package com.example.demo.services;



import com.example.demo.model.entity.Products;
import com.example.demo.repositories.ProductsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productRepository;

    public Products getProductByName(String name){
        return productRepository.findByName(name);
    }

    public Products getProductById(Integer id){
        return productRepository.findById(id);
    }

    public void saveProduct(Products product) {
        log.info("Saving new {}", product);
        productRepository.save(product);
    }
}

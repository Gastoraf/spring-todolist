//package com.example.demo.services;
//
//import com.example.demo.model.entity.MyList;
//import com.example.demo.model.Product;
//import com.example.demo.repositories.MyListRepository;
//import com.example.demo.repositories.ProductRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class ProductService {
//    private final ProductRepository productRepository;
//
//    public Product getProductByName(String name){
//        return productRepository.findByName(name);
//    }
//
//    public void saveProduct(Product product) {
//        log.info("Saving new {}", product);
//        productRepository.save(product);
//    }
//}

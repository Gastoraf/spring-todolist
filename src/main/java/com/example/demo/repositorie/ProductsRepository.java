package com.example.demo.repositorie;


import com.example.demo.model.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductsRepository extends JpaRepository<Products, Long> {
    Products findByName(String name);
    Products findById(Integer id);
}

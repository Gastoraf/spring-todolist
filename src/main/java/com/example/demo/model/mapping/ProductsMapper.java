package com.example.demo.model.mapping;

import com.example.demo.model.dto.products.ProductsDto;
import com.example.demo.model.entity.Products;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductsMapper {
    ProductsMapper INSTANCE = Mappers.getMapper(ProductsMapper.class);


    ProductsDto toDto(Products products);
}

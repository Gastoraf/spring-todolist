package com.example.demo.model.mapping;

import com.example.demo.model.dto.ProductCommentsDto;
import com.example.demo.model.entity.ProductComments;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductCommentsMapper {
    ProductCommentsMapper INSTANCE = Mappers.getMapper(ProductCommentsMapper.class);


    ProductCommentsDto modelToDto(ProductComments productComments);

    List<ProductCommentsDto> modelToDto(List<ProductComments> productComments);


}

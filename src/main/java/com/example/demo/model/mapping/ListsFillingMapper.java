package com.example.demo.model.mapping;


import com.example.demo.model.dto.listFilling.CreateListsFillingDto;

import com.example.demo.model.dto.listFilling.ListsFillingDto;
import com.example.demo.model.entity.ListsFilling;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ListsFillingMapper {
    ListsFillingMapper INSTANCE = Mappers.getMapper(ListsFillingMapper.class);

    ListsFillingDto modelToDto(ListsFilling listsFilling);

    List<ListsFillingDto> modelToDto(List<ListsFilling> listsFilling);

    ListsFilling dtoToModel(CreateListsFillingDto createListsFillingDto);
}

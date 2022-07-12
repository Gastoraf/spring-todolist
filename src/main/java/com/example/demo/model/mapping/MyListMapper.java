package com.example.demo.model.mapping;

import com.example.demo.model.dto.myList.MyListDto;
import com.example.demo.model.entity.MyList;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MyListMapper {
    MyListMapper INSTANCE = Mappers.getMapper(MyListMapper.class);

    MyListDto modelToDto(MyList myList);

    List<MyListDto> modelToDto(List<MyList> myList);
}

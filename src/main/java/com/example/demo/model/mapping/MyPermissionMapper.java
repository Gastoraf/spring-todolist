package com.example.demo.model.mapping;

import com.example.demo.model.dto.ListPermissionDto;
import com.example.demo.model.dto.MyPermissionDto;
import com.example.demo.model.entity.ListPermission;
import com.example.demo.model.entity.MyPermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MyPermissionMapper {
    MyPermissionMapper INSTANCE = Mappers.getMapper(MyPermissionMapper.class);

    MyPermissionDto modelToDto(MyPermission myPermission);

    List<MyPermissionDto> modelToDto(List<MyPermission> myPermission);
}

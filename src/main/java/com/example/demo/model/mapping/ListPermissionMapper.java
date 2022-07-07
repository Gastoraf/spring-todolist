package com.example.demo.model.mapping;

import com.example.demo.model.dto.ListPermissionDto;
import com.example.demo.model.entity.ListPermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ListPermissionMapper {
    ListPermissionMapper INSTANCE = Mappers.getMapper(ListPermissionMapper.class);

    ListPermissionDto modelToDto(ListPermission listPermission);

    List<ListPermissionDto> modelToDto(List<ListPermission> listPermission);
}

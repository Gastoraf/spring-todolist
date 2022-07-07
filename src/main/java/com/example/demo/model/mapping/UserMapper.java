package com.example.demo.model.mapping;

import com.example.demo.model.dto.ListPermissionDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.ListPermission;
import com.example.demo.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto modelToDto(User user);

    List<UserDto> modelToDto(List<User> userList);
}

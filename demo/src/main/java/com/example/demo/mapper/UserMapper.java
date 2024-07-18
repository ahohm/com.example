package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "userid", target = "userid")
    @Mapping(source = "username", target = "username")
    UserDTO userToUserDTO(User user);

    @Mapping(source = "userid", target = "userid")
    @Mapping(source = "username", target = "username")
    User userDTOToUser(UserDTO userDto);

}

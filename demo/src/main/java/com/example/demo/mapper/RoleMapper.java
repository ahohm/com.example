package com.example.demo.mapper;

import com.example.demo.dto.RoleDTO;
import com.example.demo.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "roleId", target = "roleId")
    @Mapping(source = "name", target = "roleType")
    RoleDTO RoleToRoleDTO(Role role);

    @Mapping(source = "roleId", target = "roleId")
    @Mapping(source = "roleType", target = "name")
    Role RoleDTOToRole(RoleDTO roleDTO);
}

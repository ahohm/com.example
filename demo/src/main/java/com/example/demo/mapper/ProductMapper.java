package com.example.demo.mapper;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.document.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "productid", target = "productid")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    ProductDTO ProductToproductDTO(Product product);

    @Mapping(source = "productid", target = "productid")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    Product productDTOToProduct(ProductDTO product);
}

package com.funnycode.react_springboot_account.mapper;

import com.funnycode.react_springboot_account.dto.product.ProductDTO;
import com.funnycode.react_springboot_account.dto.product.ProductDTOAdd;
import com.funnycode.react_springboot_account.entity.Color;
import com.funnycode.react_springboot_account.entity.Product;
import com.funnycode.react_springboot_account.entity.Size;

import java.util.stream.Collectors;

public class ProductMapper {


    public static ProductDTO toProductDTO(Product product){

        return ProductDTO.builder()
                .id(product.getId()).name(product.getName()).price(product.getPrice())
                .image(product.getImage()).type(product.getType())
                .colors(product.getColors().stream().map(Color::getName).collect(Collectors.toSet()))
                .sizes(product.getSizes().stream().map(Size::getName).collect(Collectors.toSet()))
                .build();


    }


    public static Product toProduct(ProductDTOAdd newProduct){
        return Product.builder().name(newProduct.getName())
                .price(newProduct.getPrice()).image(newProduct.getImage())
                .type(newProduct.getType()).build();
    }




}

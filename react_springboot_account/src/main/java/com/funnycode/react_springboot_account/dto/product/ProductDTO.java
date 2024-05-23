package com.funnycode.react_springboot_account.dto.product;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO {
    int id;
    String name;
    double price;
    String image;
    String type;
    Set<String> colors;
    Set<String> sizes;


}

package com.funnycode.react_springboot_account.dto.product;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ProductDTOUpdate {
    String name;
    double price;
    String image;
    String type;
    Set<String> colors;
    Set<String> sizes;
}

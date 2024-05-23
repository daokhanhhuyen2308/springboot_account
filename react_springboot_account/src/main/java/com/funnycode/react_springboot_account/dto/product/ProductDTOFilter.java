package com.funnycode.react_springboot_account.dto.product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTOFilter {
    private String type;
    private String color;
    private String name;
    private Double minPrice;
    private Double maxPrice;
    private Integer limit;
    private Integer offset;
}

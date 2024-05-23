package com.funnycode.react_springboot_account.mapper;

import com.funnycode.react_springboot_account.dto.product.ProductDTO;
import com.funnycode.react_springboot_account.dto.product.ProductDTOAdd;
import com.funnycode.react_springboot_account.entity.Color;
import com.funnycode.react_springboot_account.entity.Product;
import com.funnycode.react_springboot_account.entity.Size;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    @Test
    void toProductDTO() {

        //given
        Color c1 = Color.builder().id(1).name("black").build();
        Color c2 = Color.builder().id(2).name("white").build();

        Set<Color> colors = new HashSet<>();
        colors.add(c1);
        colors.add(c2);

        Size s1 = Size.builder().id(1).name("S").build();
        Size s2 = Size.builder().id(2).name("M").build();

        Set<Size> sizes = new HashSet<>();
        sizes.add(s1);
        sizes.add(s2);


        Product product = Product.builder()
                .id(1).name("product").price(500.0).image("image.png")
                .type("shirt").colors(colors).sizes(sizes).build();


        //when

        ProductDTO expected = ProductDTO.builder()
                .id(1).price(500.0)
                .name("product").image("image.png")
                .type("shirt")
                .colors(colors.stream().map(Color::getName).collect(Collectors.toSet()))
                .sizes(sizes.stream().map(Size::getName).collect(Collectors.toSet())).build();


        ProductDTO actual = ProductMapper.toProductDTO(product);

        //then

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

    }

    @Test
    void toProduct() {

        //given

        ProductDTOAdd productDTOAdd = ProductDTOAdd.builder().name("product").type("shirt").image("image.png")
                .price(500.0).build();
        //when

        Product expected = Product.builder().name("product").type("shirt").image("image.png")
                .price(500.0).build();

        Product actual = ProductMapper.toProduct(productDTOAdd);
        //then

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

    }
}
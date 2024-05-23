package com.funnycode.react_springboot_account.mapper;

import com.funnycode.react_springboot_account.dto.color.ColorDTO;
import com.funnycode.react_springboot_account.entity.Color;

public class ColorMapper {
    public static ColorDTO toColorDTO(Color color){
        return ColorDTO.builder().name(color.getName()).build();
    }
}

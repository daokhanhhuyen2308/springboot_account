package com.funnycode.react_springboot_account.mapper;

import com.funnycode.react_springboot_account.dto.size.SizeDTO;
import com.funnycode.react_springboot_account.entity.Size;
import lombok.Builder;
import lombok.Data;


public class SizeMapper {
    public static SizeDTO toSizeDTO(Size size){
        return SizeDTO.builder().name(size.getName()).build();
    }
}

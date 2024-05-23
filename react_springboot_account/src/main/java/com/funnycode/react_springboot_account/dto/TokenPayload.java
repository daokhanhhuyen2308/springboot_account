package com.funnycode.react_springboot_account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenPayload {
    private int id;
    private String username;
    private String email;
    private Set<String> authorities;
}

package com.funnycode.react_springboot_account.dto.account;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountDTOUpdate {
    private String username;
    private String email;
    private String password;
}

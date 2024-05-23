package com.funnycode.react_springboot_account.dto.account;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountRegister {
    private String username;
    private String email;
    private String password;
}

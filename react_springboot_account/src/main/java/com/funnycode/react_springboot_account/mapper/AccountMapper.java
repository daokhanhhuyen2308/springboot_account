package com.funnycode.react_springboot_account.mapper;

import com.funnycode.react_springboot_account.dto.TokenPayload;
import com.funnycode.react_springboot_account.dto.account.AccountDTO;
import com.funnycode.react_springboot_account.dto.account.AccountRegister;
import com.funnycode.react_springboot_account.entity.Account;
import com.funnycode.react_springboot_account.entity.Role;

import java.util.stream.Collectors;

public class AccountMapper {


    public static AccountDTO toAccountDTO( Account account){

        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setId(account.getId());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setEmail(account.getEmail());

        return accountDTO;

    }

    public static Account toAccount(AccountRegister register){
        Account account = new Account();
        account.setUsername(register.getUsername());
        account.setEmail(register.getEmail());
        account.setPassword(register.getPassword());
        return account;
    }

    public static TokenPayload toTokenPayload(Account account){

        return TokenPayload.builder().id(account.getId())
                .email(account.getEmail())
                .username(account.getUsername()).username(account.getUsername())
                .authorities(account.getRoles().stream().map(Role::getName).collect(Collectors.toSet()))
                .build();
    }




}

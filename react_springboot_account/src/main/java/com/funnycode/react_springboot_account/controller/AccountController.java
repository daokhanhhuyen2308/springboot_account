package com.funnycode.react_springboot_account.controller;

import com.funnycode.react_springboot_account.dto.account.AccountDTO;
import com.funnycode.react_springboot_account.dto.account.AccountDTOUpdate;
import com.funnycode.react_springboot_account.dto.account.AccountLogin;
import com.funnycode.react_springboot_account.dto.account.AccountRegister;
import com.funnycode.react_springboot_account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
@CrossOrigin("*")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/register")
    public AccountDTO register(@RequestBody AccountRegister register){
        return accountService.register(register);
    }

    @PostMapping("/login")
    public AccountDTO login(@RequestBody AccountLogin request){
        return accountService.authenticate(request);
    }

    @GetMapping("/account")
    public AccountDTO getCurrentAccount(){
        return accountService.getCurrentAccount();
    }

    @PutMapping("/update/{id}")
    public AccountDTO update(@PathVariable int id, @RequestBody AccountDTOUpdate update){
        return accountService.update(id, update);
    }

    @GetMapping("/getAllAccount")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<AccountDTO> getAllAccount(){
        return accountService.getAllAccount();
    }

}

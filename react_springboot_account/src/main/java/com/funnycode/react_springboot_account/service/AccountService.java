package com.funnycode.react_springboot_account.service;


import com.funnycode.react_springboot_account.dto.account.AccountDTO;
import com.funnycode.react_springboot_account.dto.account.AccountDTOUpdate;
import com.funnycode.react_springboot_account.dto.account.AccountLogin;
import com.funnycode.react_springboot_account.dto.account.AccountRegister;
import com.funnycode.react_springboot_account.dto.product.ProductDTO;
import com.funnycode.react_springboot_account.dto.product.ProductDTOAdd;
import com.funnycode.react_springboot_account.entity.Account;

import java.util.List;


public interface AccountService {
    public AccountDTO register(AccountRegister register);

    public AccountDTO authenticate(AccountLogin request);

    public AccountDTO getCurrentAccount();

    public AccountDTO update(int id, AccountDTOUpdate request);

    public Account getAccountLoggedIn();

    public List<AccountDTO> getAllAccount();
}

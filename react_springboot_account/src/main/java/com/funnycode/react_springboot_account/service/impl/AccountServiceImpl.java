package com.funnycode.react_springboot_account.service.impl;

import com.funnycode.react_springboot_account.dto.account.AccountDTO;
import com.funnycode.react_springboot_account.dto.account.AccountDTOUpdate;
import com.funnycode.react_springboot_account.dto.account.AccountLogin;
import com.funnycode.react_springboot_account.dto.account.AccountRegister;
import com.funnycode.react_springboot_account.entity.*;
import com.funnycode.react_springboot_account.exception.CustomHandleException;
import com.funnycode.react_springboot_account.mapper.AccountMapper;
import com.funnycode.react_springboot_account.repository.*;
import com.funnycode.react_springboot_account.service.AccountService;
import com.funnycode.react_springboot_account.utils.JwtTokenUtils;
import com.funnycode.react_springboot_account.validator.EmailValidator;
import com.funnycode.react_springboot_account.validator.ValidatorUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;
    PasswordEncoder passwordEncoder;
    JwtTokenUtils jwtTokenUtils;
    RoleRepository roleRepository;

    @Override
    public AccountDTO register(AccountRegister register) {

        String username = register.getUsername();
        String password = register.getPassword();
        String email = register.getEmail();

        boolean emailExist = accountRepository.findByEmail(email).isPresent();
        boolean usernameExist = accountRepository.findByUsername(username).isPresent();


        if(username == null){
            throw CustomHandleException.badRequestException("Username cannot be null");
        }

        if(email == null){
            throw CustomHandleException.badRequestException("Email cannot be null");
        }

        if(password == null){
            throw CustomHandleException.badRequestException("Password cannot be null");
        }

        if(!EmailValidator.isValidEmail(email)){
            throw CustomHandleException.badRequestException("Invalid email");
        }

        if(!ValidatorUtils.isValidUsername(username)){
            throw CustomHandleException.badRequestException("Invalid username");
        }

        if(!ValidatorUtils.isValidPassword(password)){
            throw CustomHandleException.badRequestException("Invalid password");
        }

        if(emailExist){
            throw new IllegalStateException("Username already taken");
        }
        if(usernameExist){
            throw new IllegalStateException("Email already taken");
        }


        Account account = AccountMapper.toAccount(register);

        account.setPassword(passwordEncoder.encode(password));

        Set<Role> authorities = new HashSet<>();
        authorities.add(roleRepository.findByNameIgnoreCase("USER")
        .orElseThrow(() -> CustomHandleException.badRequestException("Default role not found")));
        account.setRoles(authorities);

        account = accountRepository.save(account);



        return builderDTO(account);
    }

    @Override
    public AccountDTO authenticate(AccountLogin request) {

        Optional<Account> accountOptional = accountRepository.findByEmail(request.getEmail());

        boolean isAuthenticate = false;

        String email = request.getEmail();
        String password = request.getPassword();

        if(email==null){
            throw CustomHandleException.badRequestException("Please enter your email");
        }

        if(!ValidatorUtils.isValidPassword(password)){
            throw CustomHandleException.badRequestException("Password invalid");
        }

        if(accountOptional.isPresent()){
            Account account = accountOptional.get();
            if(passwordEncoder.matches(account.getPassword(),password)){
                isAuthenticate = true;
                System.out.println("Login Successfully");
            }
        }else{
            throw CustomHandleException.notFountException("Email not found");
        }

        return builderDTO(accountOptional.get());
    }

    @Override
    public AccountDTO update(int id, AccountDTOUpdate request) {

        Account loggedInAccount = getAccountLoggedIn();

        if (loggedInAccount.getId() != id){
            throw CustomHandleException.badRequestException("You can only update your own information");
        }

        loggedInAccount.setUsername(request.getUsername());
        loggedInAccount.setPassword(passwordEncoder.encode(request.getPassword()));
        accountRepository.save(loggedInAccount);


        return builderDTO(loggedInAccount);
    }

    @Override
    public AccountDTO getCurrentAccount() {
        Account account = getAccountLoggedIn();
        if(account == null){
            throw CustomHandleException.badRequestException("You are not logged in");
        }

        return builderDTO(account);

    }

    @Override
    public Account getAccountLoggedIn() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            Optional<Account> accountOptional = accountRepository.findByEmail(email);
            if (accountOptional.isPresent()) {
                return accountOptional.get();
            }
        }
        return null;

    }

    @Override
    public List<AccountDTO> getAllAccount() {

        List<Account> accounts = accountRepository.findAll();

        return accounts.stream().map(AccountMapper::toAccountDTO).toList();



    }

    private AccountDTO builderDTO(Account account){
        final long days = 24 * 60 * 60 * 30;
        String token = jwtTokenUtils.generateToken(AccountMapper.toTokenPayload(account), days);
        AccountDTO accountDTO = AccountMapper.toAccountDTO(account);
        accountDTO.setToken(token);
        return accountDTO;
    }

}

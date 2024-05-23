package com.funnycode.react_springboot_account.service.impl;

import com.funnycode.react_springboot_account.dto.account.AccountDTO;
import com.funnycode.react_springboot_account.dto.account.AccountLogin;
import com.funnycode.react_springboot_account.dto.account.AccountRegister;
import com.funnycode.react_springboot_account.entity.Account;
import com.funnycode.react_springboot_account.entity.Role;
import com.funnycode.react_springboot_account.exception.CustomHandleException;
import com.funnycode.react_springboot_account.mapper.AccountMapper;
import com.funnycode.react_springboot_account.repository.AccountRepository;
import com.funnycode.react_springboot_account.repository.RoleRepository;
import com.funnycode.react_springboot_account.utils.JwtTokenUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @InjectMocks
    AccountServiceImpl accountService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    JwtTokenUtils jwtTokenUtils;

    @Mock
    RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void testAuthenticate_Success() {

        //given
        AccountLogin login = AccountLogin.builder()
                .email("email@gmail.com").password("password").build();

        Role r1 = Role.builder().id(1).name("ADMIN").build();
        Role r2 = Role.builder().id(2).name("MANAGER").build();
        Role r3 = Role.builder().id(3).name("USER").build();

        Set<Role> roles = new HashSet<>();
        roles.add(r1);
        roles.add(r2);
        roles.add(r3);

       Optional<Account> account = Optional.of(Account.builder()
                        .id(1).username("username").email("email@gmail.com").password("password")
               .roles(roles).build());

        AccountDTO expected = AccountDTO.builder().id(1).username("username")
                .email("email@gmail.com").token("BEARER").build();

        //when
        when(accountRepository.findByEmail(login.getEmail())).thenReturn(account);

        when(passwordEncoder.matches(login.getPassword(), "password")).thenReturn(true);

        when(jwtTokenUtils.generateToken(AccountMapper.toTokenPayload(account.get()),5184000)).thenReturn("BEARER");
        AccountDTO actual = accountService.authenticate(login);

        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void testAuthenticate_Failure() {

        //given
        AccountLogin login = AccountLogin.builder()
                .email("email@gmail.com").password("password").build();


        //when
        when(accountRepository.findByEmail(login.getEmail())).thenReturn(Optional.empty());

        CustomHandleException exception = assertThrows(CustomHandleException.class, () -> {
            accountService.authenticate(login);
        });

        //then
        assertEquals("Email not found", exception.getCustomError().getMessage());

    }


    @Test
    void testRegister_Success() {
    }

    @Test
    void testRegister_Failure() {

    }

    @Test
    void update() {
    }

    @Test
    void getCurrentAccount() {
    }

    @Test
    void getAccountLoggedIn() {
    }

    @Test
    void getAllAccount() {
    }
}
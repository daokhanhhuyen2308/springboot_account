package com.funnycode.react_springboot_account.mapper;

import com.funnycode.react_springboot_account.dto.TokenPayload;
import com.funnycode.react_springboot_account.dto.account.AccountDTO;
import com.funnycode.react_springboot_account.dto.account.AccountRegister;
import com.funnycode.react_springboot_account.entity.Account;
import com.funnycode.react_springboot_account.entity.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class AccountMapperTest {

    @Test
    void toAccountDTO() {

        //given
        Account account = Account.builder().id(1).email("email@gmail.com")
                .username("username")
                .build();
        //when
        AccountDTO actual = AccountMapper.toAccountDTO(account);

        AccountDTO expected = AccountDTO.builder().id(1).email("email@gmail.com")
                .username("username").build();
        //then
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getUsername(), actual.getUsername());

    }

    @Test
    void toAccount() {

        //given
        AccountRegister register = AccountRegister.builder()
                .username("username").email("email@gmail.com")
                .password("password").build();

        //when
        Account expected = Account.builder().username("username")
                .email("email@gmail.com").password("password").build();

        Account actual = AccountMapper.toAccount(register);

        //then

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void toTokenPayload() {

        //given

        Role r1 = Role.builder().id(1).name("ADMIN").build();
        Role r2 = Role.builder().id(2).name("MANAGER").build();
        Role r3 = Role.builder().id(3).name("USER").build();

        Set<Role> roles = new HashSet<>();
        roles.add(r1);
        roles.add(r2);
        roles.add(r3);


        Account account = Account.builder().id(1).username("username").email("email@gmail.com")
                .roles(roles).build();

        //when

        Set<String> authorities = roles.stream().map(Role::getName).collect(Collectors.toSet());

        TokenPayload expected = TokenPayload.builder().id(1).username("username").email("email@gmail.com")
                .authorities(authorities).build();

        TokenPayload actual = AccountMapper.toTokenPayload(account);

        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

    }
}
package com.funnycode.react_springboot_account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funnycode.react_springboot_account.dto.account.AccountDTO;
import com.funnycode.react_springboot_account.dto.account.AccountRegister;
import com.funnycode.react_springboot_account.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRegister() throws Exception {

        //given
        AccountRegister register = AccountRegister.builder()
                .username("username")
                .email("email@gmail.com").password("password")
                .build();

        AccountDTO expected = AccountDTO.builder().username("username").email("email@gmail.com").build();

        //when
        when(accountService.register(register)).thenReturn(expected);

        mockMvc.perform(post("/api/auth/register"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.username").value("username"))
                .andExpect(jsonPath("$.email").value("email@gmail.com"));

        //then
        verify(accountService, times(1)).register(register);
        verifyNoMoreInteractions(accountService);



    }


    @Test
    void testLogin() {
    }

    @Test
    void testGetCurrentAccount() {
    }

    @Test
    void testUpdate() {
    }

    @Test
    void testGetAllAccount() {
    }
}
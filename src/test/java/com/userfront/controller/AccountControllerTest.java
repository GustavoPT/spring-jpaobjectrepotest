package com.userfront.controller;

import com.userfront.models.User;
import com.userfront.repositories.AccountRepository;
import com.userfront.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testView() throws Exception {
        User user = Mockito.mock(User.class);
        Mockito.when(user.getAccounts()).thenReturn(new ArrayList<>());
        Mockito.when(userRepository.findByUsername(any())).thenReturn(user);
        this.mockMvc.perform(get("/account/view")).andExpect(status().isOk()).andExpect(view().name("accounts")).andExpect(model().attributeExists("accounts"));
    }

    @Test
    void shouldCreateNewCustomer() throws Exception {
        User user = Mockito.mock(User.class);
        Mockito.when(user.getAccounts()).thenReturn(new ArrayList<>());
        Mockito.when(userRepository.findByUsername(any())).thenReturn(user);
        this.mockMvc.perform(post("/account/save").param("balance", "100").param("type", "SAVINGS")).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/account/view"));
    }

    //

}


package com.userfront.controller;

import com.userfront.repositories.AccountRepository;
import com.userfront.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest {

    // test the get and [pst emthods


    @MockBean
    private UserRepository userRepository;
    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testView() throws Exception {

        this.mockMvc.perform(get("/register/view")).andExpect
                (status().isOk()).andExpect(view().
                name("registrationclient"));
    }
    @Test
    public void testPost(){

    }

}

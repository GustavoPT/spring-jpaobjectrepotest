package com.userfront.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(RegistrationController.class)
public class TestRegistrationController {

    // test the get and [pst emthods
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGet() throws Exception {

        mockMvc.perform(get("/register")).andExpect
                (status().isOk()).andExpect(view().
                name("registrationClient"));
    }
    @Test
    public void testPost(){

    }

}

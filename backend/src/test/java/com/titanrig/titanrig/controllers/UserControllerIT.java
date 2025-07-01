package com.titanrig.titanrig.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.titanrig.titanrig.dto.UserInsertDTO;
import com.titanrig.titanrig.dto.UserUpdateDTO;
import com.titanrig.titanrig.tests.TokenUtil;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerIT {

     @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private ObjectMapper objectMapper;

    private String clientUsername;
    private String clientPassword;
    private String adminUsername;
    private String adminPassword;

    @BeforeEach
    void setUp() {
        clientUsername = "alex@gmail.com";
        clientPassword = "123456";
        adminUsername = "maria@gmail.com";
        adminPassword = "123456";
    }

    @Test
    public void findMeShouldReturnUserWhenClientAuthenticated() throws Exception {
        String token = tokenUtil.obtainAccessToken(mockMvc, clientUsername, clientPassword);

        ResultActions result = mockMvc.perform(get("/users/me")
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.email").value(clientUsername));
    }

    @Test
    public void deleteShouldReturnNoContentWhenIdExists() throws Exception {
        String token = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);

        ResultActions result = mockMvc.perform(delete("/users/2")
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNoContent());
    }
}

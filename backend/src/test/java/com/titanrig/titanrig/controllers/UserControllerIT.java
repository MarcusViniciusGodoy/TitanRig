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

import java.time.LocalDate;

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
        adminUsername = "leticia@gmail.com";
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
    public void findMeShouldReturnUnauthorizedWhenNotAuthenticated() throws Exception {
        ResultActions result = mockMvc.perform(get("/users/me")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnauthorized());
    }

    @Test
    public void findAllShouldReturnForbiddenWhenClientAuthenticated() throws Exception {
        String token = tokenUtil.obtainAccessToken(mockMvc, clientUsername, clientPassword);

        ResultActions result = mockMvc.perform(get("/users")
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isForbidden());
    }

    @Test
    public void findAllShouldReturnPagedUsersWhenAdminAuthenticated() throws Exception {
        String token = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);

        ResultActions result = mockMvc.perform(get("/users")
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.content").exists());
    }

    @Test
    public void findByIdShouldReturnUserWhenAdminAuthenticated() throws Exception {
        String token = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);

        ResultActions result = mockMvc.perform(get("/users/1")
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void insertShouldCreateUserWhenValidData() throws Exception {
        UserInsertDTO dto = new UserInsertDTO();
            dto.setName("Novo Usuário");
            dto.setEmail("novo@email.com");
            dto.setPassword("123456");
            dto.setPhone("11999999999");
            dto.setCpf("12345678900");
            dto.setBirthDate(LocalDate.of(2000, 1, 1));

        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions result = mockMvc.perform(post("/users")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.email").value("novo@email.com"));
    }

    @Test
    public void updateShouldUpdateUserWhenIdExists() throws Exception {
        String token = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);

        UserUpdateDTO dto = new UserUpdateDTO();
            dto.setName("Maria Atualizada");
            dto.setEmail("mariaAtualizada@email.com");
            dto.setPhone("11988887777");
            dto.setCpf("12345678900");
            dto.setBirthDate(LocalDate.of(1990, 1, 1));

        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions result = mockMvc.perform(put("/users/1")
            .header("Authorization", "Bearer " + token)
            .content(jsonBody)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").value(1));
        result.andExpect(jsonPath("$.name").value("Maria Atualizada"));
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

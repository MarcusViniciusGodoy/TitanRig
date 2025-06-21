package com.titanrig.titanrig.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.titanrig.titanrig.tests.TokenUtil;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TokenUtil tokenUtil;

	private long existingId;
	private long nonExistingId;
	
	private String visitorUsername;
	private String visitorPassword;
	private String memberUsername;
	private String memberPassword;
	@BeforeEach
	void setUp() throws Exception {

		existingId = 1L;
		nonExistingId = 100000L;
		
		visitorUsername = "bob@gmail.com";
		visitorPassword = "123456";
		memberUsername = "ana@gmail.com";
		memberPassword = "123456";
	}

	@Test
	public void findByIdShouldReturnUnauthorizedWhenNoTokenGiven() throws Exception {

		ResultActions result =
				mockMvc.perform(get("/products/{id}", existingId)
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isUnauthorized());
	}

	@Test
	public void findByIdShouldReturnProductWhenUserVisitorAuthenticated() throws Exception {

		String accessToken = tokenUtil.obtainAccessToken(mockMvc, visitorUsername, visitorPassword);
		
		ResultActions result =
				mockMvc.perform(get("/products/{id}", existingId)
					.header("Authorization", "Bearer " + accessToken)
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").value(existingId));
		result.andExpect(jsonPath("$.name").isNotEmpty());
		result.andExpect(jsonPath("$.description").isNotEmpty());
		result.andExpect(jsonPath("$.price").isNotEmpty());
		result.andExpect(jsonPath("$.imgUrl").isNotEmpty());
	}
}


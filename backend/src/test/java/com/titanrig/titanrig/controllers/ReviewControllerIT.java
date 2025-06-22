package com.titanrig.titanrig.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.titanrig.titanrig.dto.ReviewDTO;
import com.titanrig.titanrig.tests.TokenUtil;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ReviewControllerIT {

    @Autowired
	private MockMvc mockMvc;

	@Autowired
	private TokenUtil tokenUtil;

	@Autowired
	private ObjectMapper objectMapper;

	private String visitorUsername;
	private String visitorPassword;
	private String memberUsername;
	private String memberPassword;
	
	@BeforeEach
	void setUp() throws Exception {
		
		visitorUsername = "bob@gmail.com";
		visitorPassword = "123456";
		memberUsername = "ana@gmail.com";
		memberPassword = "123456";
	}

    @Test
	public void insertShouldReturnUnauthorizedWhenNotValidToken() throws Exception {

		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setText("Gostei do produto!");
		reviewDTO.setProductId(1L);

		String jsonBody = objectMapper.writeValueAsString(reviewDTO);
		
		ResultActions result =
				mockMvc.perform(post("/reviews")
						.content(jsonBody)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isUnauthorized());
	}
}

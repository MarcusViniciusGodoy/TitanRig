package com.titanrig.titanrig.services;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.titanrig.titanrig.dto.CategoryDTO;
import com.titanrig.titanrig.dto.ProductDTO;
import com.titanrig.titanrig.tests.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductResourceIT {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private TokenUtil tokenUtil;

	private Long existingId;
	private Long countTotalProducts;
	
	private String username, password, bearerToken;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		countTotalProducts = 5L;
		
		username = "maria@gmail.com";
		password = "123456";
		
		bearerToken = tokenUtil.obtainAccessToken(mockMvc, username, password);
	}
	
    @Test
	public void findAllShouldReturnSortedPageWhenSortByName() throws Exception {
		
		ResultActions result = 
				mockMvc.perform(get("/products?page=0&size=12&sort=name,asc")
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.totalElements").value(countTotalProducts));
		result.andExpect(jsonPath("$.content").exists());		
		result.andExpect(jsonPath("$.content[0].name").value("Aorus"));
		result.andExpect(jsonPath("$.content[1].name").value("Corsair"));
		result.andExpect(jsonPath("$.content[2].name").value("MAtrix"));		
	}

    @Test
	public void updateShouldReturnProductDTOWhenIdExists() throws Exception {
		
		 ProductDTO productDTO = new ProductDTO();
    	productDTO.setName("Produto Teste Atualizado");
    	productDTO.setDescription("Descrição atualizada do produto");
    	productDTO.setPrice(150.0);
    	productDTO.setImgUrl("https://img.com/produto.jpg");

    	CategoryDTO categoryDTO = new CategoryDTO();
    	categoryDTO.setId(1L); 
    	productDTO.getCategories().add(categoryDTO);

    	String jsonBody = objectMapper.writeValueAsString(productDTO);

    	ResultActions result = 
            mockMvc.perform(put("/products/{id}", existingId)
                .header("Authorization", "Bearer " + bearerToken)    
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    
    	result.andExpect(status().isOk());
    	result.andExpect(jsonPath("$.id").value(existingId));
    	result.andExpect(jsonPath("$.name").value(productDTO.getName()));
    	result.andExpect(jsonPath("$.description").value(productDTO.getDescription()));
	}
}

package com.titanrig.titanrig.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CategoryControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findByIdShouldReturnCategoryWhenIdExists() throws Exception {
        long existingId = 1L;

        mockMvc.perform(get("/categories/{id}", existingId)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(existingId));
    }
    
    @Test
    public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
        long nonExistingId = 9999L;

        mockMvc.perform(get("/categories/{id}", nonExistingId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findAllShouldReturnPagedCategory() throws Exception {
        mockMvc.perform(get("/categories")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").exists()); 
    }

    @Test
    public void findAllShouldReturnPagedCategories() throws Exception {
        mockMvc.perform(get("/categories")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content.length()").value(3)) 
                .andExpect(jsonPath("$.content[0].id").exists())
                .andExpect(jsonPath("$.content[0].name").exists());
    }

    @Test
    public void findAllShouldReturnFirstPageWithSizeTwo() throws Exception {
        mockMvc.perform(get("/categories?page=0&size=2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.totalElements").value(3))
                .andExpect(jsonPath("$.totalPages").value(2));
    }

    @Test
    public void findAllShouldReturnCategoriesSortedByNameAsc() throws Exception {
        mockMvc.perform(get("/categories?sort=name,asc")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Gabinetes"))
                .andExpect(jsonPath("$.content[1].name").value("Memorias"))
                .andExpect(jsonPath("$.content[2].name").value("Placas mães"));
    }

    @Test
    public void findAllShouldReturnCategoriesSortedByNameDesc() throws Exception {
        mockMvc.perform(get("/categories?sort=name,desc")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Placas mães"))
                .andExpect(jsonPath("$.content[1].name").value("Memorias"))
                .andExpect(jsonPath("$.content[2].name").value("Gabinetes"));
    }

    @Test
    public void findAllShouldReturnValidCategoryStructure() throws Exception {
        mockMvc.perform(get("/categories")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").isNumber())
                .andExpect(jsonPath("$.content[0].name").isString());
    }

    @Test
    public void findByIdShouldReturnBadRequestWhenIdIsInvalid() throws Exception {
        mockMvc.perform(get("/categories/{id}", -1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}

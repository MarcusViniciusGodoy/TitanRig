package com.titanrig.titanrig.dto;

import java.io.Serializable;

import com.titanrig.titanrig.entities.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public CategoryDTO(Category entity){
        id = entity.getId();
        name = entity.getName();
    }
}

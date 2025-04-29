package com.titanrig.titanrig.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.titanrig.titanrig.entities.Category;
import com.titanrig.titanrig.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;

    @Size(min = 5, max = 60, message = "Deve ter entre 5 e 60 caracteres")
    @NotBlank(message = "Campo obrigatório")
    private String name;

    @NotBlank(message = "Campo obrigatório")
    private String description;

    @Positive(message = "Preço deve ser um valor positivo")
    private Double price;

    private List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
    }

    public ProductDTO(Product entity, Set<Category> categories) {
        this(entity);
        categories.forEach(x -> this.categories.add(new CategoryDTO(x)));
    }
}

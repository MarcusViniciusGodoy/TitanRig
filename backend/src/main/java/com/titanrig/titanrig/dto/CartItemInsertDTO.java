package com.titanrig.titanrig.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemInsertDTO {

    @NotNull(message = "Produto obrigatório")
    private Long productId;

    @NotNull(message = "Quantidade obrigatória")
    @Min(value = 1, message = "Quantidade mínima é 1")
    private Integer quantity;

    public CartItemInsertDTO() {}

    public CartItemInsertDTO(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}

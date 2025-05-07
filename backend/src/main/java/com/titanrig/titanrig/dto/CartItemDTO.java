package com.titanrig.titanrig.dto;

import com.titanrig.titanrig.entities.CartItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {

    private ProductDTO product;
    private Integer quantity;

    public CartItemDTO(CartItem entity) {
        this.product = new ProductDTO(entity.getProduct());
        this.quantity = entity.getQuantity(); 
    }
}

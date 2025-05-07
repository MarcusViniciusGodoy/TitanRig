package com.titanrig.titanrig.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {

    private Long id;
    private ClientDTO client;
    private List<CartItemDTO> items = new ArrayList<>();
    
}

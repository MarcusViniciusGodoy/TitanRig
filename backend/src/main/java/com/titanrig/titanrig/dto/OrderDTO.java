package com.titanrig.titanrig.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.titanrig.titanrig.entities.OrderStatus;

import jakarta.validation.constraints.NotEmpty;

public class OrderDTO {

    private Long id;
	private Instant moment;
	private OrderStatus status;
	
	private ClientDTO client;
	
	private PaymentDTO payment;

    @NotEmpty(message = "Deve ter pelo menos um item")
	private List<OrderItemDTO> items = new ArrayList<>();
}

package com.titanrig.titanrig.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.titanrig.titanrig.entities.Order;
import com.titanrig.titanrig.entities.OrderItem;
import com.titanrig.titanrig.entities.OrderStatus;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long id;
	private Instant moment;
	private OrderStatus status;
	
	private ClientDTO client;
	
	private PaymentDTO payment;

    @NotEmpty(message = "Deve ter pelo menos um item")
	private List<OrderItemDTO> items = new ArrayList<>();

	public OrderDTO(Order entity) {
		this.id = entity.getId();
		this.moment = entity.getMoment();
		this.status = entity.getStatus();
		this.client = new ClientDTO(entity.getClient());
		this.payment = (entity.getPayment() == null) ? null : new PaymentDTO(entity.getPayment());
		for (OrderItem item : entity.getItems()) {
			OrderItemDTO itemDto = new OrderItemDTO(item);
			items.add(itemDto);
		}
 	}

	 public Double getTotal() {
		double sum = 0.0;
		for (OrderItemDTO item : items) {
			sum += item.getSubTotal();
		}
		return sum;
	}
}

package com.titanrig.titanrig.dto;

import java.time.Instant;

import com.titanrig.titanrig.entities.Payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaymentDTO {

    private Long id;
    private Instant moment;

    public PaymentDTO(Payment entity) {
		id = entity.getId();
		moment = entity.getMoment();
	}
}

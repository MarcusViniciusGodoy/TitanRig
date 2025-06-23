package com.titanrig.titanrig.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewInsertDTO {

    @NotBlank(message = "Texto da avaliação não pode estar em branco")
    private String text;

    @NotNull
    private Long productId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}


package com.titanrig.titanrig.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewInsertDTO {

    @NotBlank(message = "Texto da avaliação não pode estar em branco")
    private String text;

    @NotNull
    private Long movieId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}


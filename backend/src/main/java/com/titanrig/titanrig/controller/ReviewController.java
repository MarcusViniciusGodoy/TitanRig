package com.titanrig.titanrig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.titanrig.titanrig.dto.ReviewDTO;
import com.titanrig.titanrig.dto.ReviewInsertDTO;
import com.titanrig.titanrig.services.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @PostMapping
    public ResponseEntity<ReviewDTO> insert(@Valid @RequestBody ReviewInsertDTO dto) {
        ReviewDTO result = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}


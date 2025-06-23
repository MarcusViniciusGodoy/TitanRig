package com.titanrig.titanrig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.titanrig.titanrig.dto.ReviewDTO;
import com.titanrig.titanrig.dto.ReviewInsertDTO;
import com.titanrig.titanrig.entities.Product;
import com.titanrig.titanrig.entities.Review;
import com.titanrig.titanrig.entities.User;
import com.titanrig.titanrig.repositories.ProductRepository;
import com.titanrig.titanrig.repositories.ReviewRepository;
import com.titanrig.titanrig.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AuthService authService;

    @Transactional
    public ReviewDTO insert(ReviewInsertDTO dto) {

        User user = authService.authenticated();
        
        Product product = productRepository.findById(dto.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Review review = new Review();
        review.setText(dto.getText());
        review.setProduct(product);
        review.setUser(user);

        review = repository.save(review);
        return new ReviewDTO(review);
    }
}

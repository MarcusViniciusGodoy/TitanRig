package com.titanrig.titanrig.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titanrig.titanrig.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

    List<Review> findByProductId(Long product_id);
}

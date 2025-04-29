package com.titanrig.titanrig.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.titanrig.titanrig.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}

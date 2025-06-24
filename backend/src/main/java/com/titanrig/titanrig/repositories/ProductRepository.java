package com.titanrig.titanrig.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.titanrig.titanrig.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    @Query("SELECT obj FROM Product obj WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<Product> searchByName(String name, Pageable pageable);

    /*@Query("SELECT m FROM Product m WHERE m.product.id = :productId ORDER BY m.title")
    Page<Product> findByGenre(@Param("productId") Long genreId, Pageable pageable);

    @Query("SELECT m FROM Product m ORDER BY m.title")
    Page<Product> findAllOrdered(Pageable pageable);*/
}

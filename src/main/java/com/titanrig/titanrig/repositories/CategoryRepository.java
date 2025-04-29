package com.titanrig.titanrig.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.titanrig.titanrig.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}

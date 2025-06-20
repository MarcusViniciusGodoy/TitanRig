package com.titanrig.titanrig.repositories;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.titanrig.titanrig.entities.Product;
import com.titanrig.titanrig.tests.Factory;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;

    private long existingId;
    private long countTotalProducts;
    private long nonExistingId;

    @BeforeEach
    void setUp() throws Exception{
        existingId = 1L;
        countTotalProducts = 25L;
        nonExistingId = 100L;
    }

    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull(){
        Product product = Factory.createProduct();
        product.setId(null);

        product = repository.save(product);

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countTotalProducts + 1, product.getId());
    }
}

package com.titanrig.titanrig.tests;

import com.titanrig.titanrig.dto.ProductDTO;
import com.titanrig.titanrig.entities.Category;
import com.titanrig.titanrig.entities.Product;

public class Factory {

    public static Product createProduct(){
        Product product = new Product();
        product.getCategories().add(createCategory());
        return product;
    }

    public static ProductDTO createProductDTO(){
        Product product = createProduct();
        return new ProductDTO(product, product.getCategories());
    }

    public static Category createCategory(){
        return new Category(2L, "Electonics", null);
    }
}

package com.titanrig.titanrig.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTests {

	@Test
	public void movieShouldHaveCorrectStructure() {
	
		Product entity = new Product();
		entity.setId(1L);
		entity.setName("Name");
		entity.setDescription("Description");
		entity.setPrice(10.0);
		entity.setImgUrl("https://imgurl.com/img.png");
	
		Assertions.assertNotNull(entity.getId());
		Assertions.assertNotNull(entity.getName());
		Assertions.assertNotNull(entity.getDescription());
		Assertions.assertNotNull(entity.getPrice());
		Assertions.assertNotNull(entity.getImgUrl());
	}
}


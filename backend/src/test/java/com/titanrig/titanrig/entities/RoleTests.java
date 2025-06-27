package com.titanrig.titanrig.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoleTests {

	@Test
	public void roleShouldHaveCorrectStructure() {
	
		Role entity = new Role();
		entity.setId(1L);
		entity.setAuthority("ROLE_CLIENT");
	
		Assertions.assertNotNull(entity.getId());
		Assertions.assertNotNull(entity.getAuthority());
	}
}

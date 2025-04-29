package com.titanrig.titanrig.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.titanrig.titanrig.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}

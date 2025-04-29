package com.titanrig.titanrig.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.titanrig.titanrig.entities.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long>{

}

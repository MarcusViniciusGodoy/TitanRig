package com.titanrig.titanrig.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.titanrig.titanrig.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}

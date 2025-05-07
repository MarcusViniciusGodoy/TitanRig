package com.titanrig.titanrig.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titanrig.titanrig.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}

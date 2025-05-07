package com.titanrig.titanrig.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titanrig.titanrig.entities.OrderItem;
import com.titanrig.titanrig.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{

}

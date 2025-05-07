package com.titanrig.titanrig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.titanrig.titanrig.dto.OrderDTO;
import com.titanrig.titanrig.entities.Order;
import com.titanrig.titanrig.repositories.OrderItemRepository;
import com.titanrig.titanrig.repositories.OrderRepository;
import com.titanrig.titanrig.repositories.ProductRepository;
import com.titanrig.titanrig.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order);
    }
}

package com.titanrig.titanrig.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.titanrig.titanrig.dto.OrderDTO;
import com.titanrig.titanrig.dto.OrderItemDTO;
import com.titanrig.titanrig.entities.Order;
import com.titanrig.titanrig.entities.OrderItem;
import com.titanrig.titanrig.entities.OrderStatus;
import com.titanrig.titanrig.entities.Product;
import com.titanrig.titanrig.entities.User;
import com.titanrig.titanrig.repositories.OrderItemRepository;
import com.titanrig.titanrig.repositories.OrderRepository;
import com.titanrig.titanrig.repositories.ProductRepository;
import com.titanrig.titanrig.services.exceptions.ResourceNotFoundException;
import com.titanrig.titanrig.services.exceptions.UnauthorizedException;

import jakarta.persistence.EntityNotFoundException;

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
    
   /*  @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order);
    }*/

    @Transactional
    public OrderDTO insert(OrderDTO dto){

        Order order = new Order();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = userService.authenticated();
        order.setClient(user);

        for (OrderItemDTO itemDTO : dto.getItems()){
            Product product = productRepository.getReferenceById(itemDTO.getProductId());
            OrderItem item = new OrderItem(order, product, itemDTO.getQuantity(), product.getPrice());
            order.getItems().add(item);
        }

        repository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO();
    }

    @Transactional
    public OrderDTO update(Long id, OrderDTO dto){
        try {
            Order entity = repository.getReferenceById(id);
            Long authenticatedUserId = userService.findMe().getId();
            
            if (!entity.getClient().getId().equals(authenticatedUserId)){
                throw new UnauthorizedException("Pedido não pertence ao usuário logado.");
            }

            if (!entity.getStatus().equals(OrderStatus.WAITING_PAYMENT)) {
                throw new IllegalArgumentException("Pedido já processado. Não pode ser alterado.");
            }
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new OrderDTO(entity);
        } catch (EntityNotFoundException  e) {
            throw new ResourceNotFoundException("Pedido não encontrado: " + id);
        }
    }

    private void copyDtoToEntity(OrderDTO dto, Order entity){
        
        entity.getItems().clear();
        for (OrderItemDTO orderItemDto : dto.getItems()){
            OrderItem orderItem = new OrderItem();
            Product product = new Product();
            product.setId(orderItemDto.getProductId());
            orderItem.setQuantity(orderItemDto.getQuantity());
            entity.getItems().add(orderItem);
        }
    }
}

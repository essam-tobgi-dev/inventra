package com.chainlink.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chainlink.dtos.OrderDTO;
import com.chainlink.exceptions.OutOfStockException;
import com.chainlink.mappers.OrderMapper;
import com.chainlink.models.Inventory;
import com.chainlink.models.Order;
import com.chainlink.models.OrderItem;
import com.chainlink.repositories.InventoryRepository;
import com.chainlink.repositories.OrderItemRepository;
import com.chainlink.repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                     .map(OrderMapper::toDTO)
                     .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(UUID id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(OrderMapper::toDTO).orElse(null);
    }

    public OrderDTO createOrder(OrderDTO orderDTO) throws OutOfStockException {
        Order order = OrderMapper.toEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        List<OrderItem> orderItems = orderItemRepository.findAllByOrder(savedOrder);
        for (var orderItem: orderItems){
            Inventory inventory = inventoryRepository
                .findByProduct(orderItem.getProduct()).orElseThrow();
            if (inventory.getQuantity() < orderItem.getQuantity()){
                throw new OutOfStockException(
                    "This item is out of stock " + orderItem.getProduct().getName()
                );
            }
            inventory.setQuantity(inventory.getQuantity() - orderItem.getQuantity());
        }
        return OrderMapper.toDTO(savedOrder);
    }

    public OrderDTO updateOrder(UUID id, OrderDTO orderDTO) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()) {
            Order orderToUpdate = OrderMapper.toEntity(orderDTO);
            orderToUpdate.setId(id);
            Order updatedOrder = orderRepository.save(orderToUpdate);
            return OrderMapper.toDTO(updatedOrder);
        }
        return null;
    }

    public boolean deleteOrder(UUID id) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()) {
            orderRepository.delete(existingOrder.get());
            return true;
        }
        return false;
    }
}

package com.chainlink.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chainlink.dtos.OrderItemDTO;
import com.chainlink.mappers.OrderItemMapper;
import com.chainlink.models.Order;
import com.chainlink.models.OrderItem;
import com.chainlink.models.Product;
import com.chainlink.repositories.OrderItemRepository;
import com.chainlink.repositories.OrderRepository;
import com.chainlink.repositories.ProductRepository;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderItemDTO> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItems
            .stream()
            .map(OrderItemMapper::toDTO)
            .collect(Collectors.toList());
    }

    public OrderItemDTO getOrderItemById(UUID id) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        return orderItem.map(OrderItemMapper::toDTO).orElse(null);
    }

    public OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO) {
        Product product = productRepository.findById(orderItemDTO.getProductId())
            .orElseThrow(() -> new RuntimeException("Product not found"));
    
        Order order = orderRepository.findById(orderItemDTO.getOrderId())
            .orElseThrow(() -> new RuntimeException("Order not found"));
    
        OrderItem orderItem = OrderItemMapper.toEntity(orderItemDTO, product, order);
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return OrderItemMapper.toDTO(savedOrderItem);
    }

    public OrderItemDTO updateOrderItem(UUID id, OrderItemDTO orderItemDTO) {
        Optional<OrderItem> optionalOrderItem = orderItemRepository.findById(id);
        if (optionalOrderItem.isEmpty()) {
            return null;
        }
    
        OrderItem existingOrderItem = optionalOrderItem.get();
    
        Product product = productRepository.findById(orderItemDTO.getProductId())
            .orElseThrow(() -> new RuntimeException("Product not found"));
    
        Order order = orderRepository.findById(orderItemDTO.getOrderId())
            .orElseThrow(() -> new RuntimeException("Order not found"));
    
        existingOrderItem.setProduct(product);
        existingOrderItem.setOrder(order);
        existingOrderItem.setQuantity(orderItemDTO.getQuantity());
    
        OrderItem saved = orderItemRepository.save(existingOrderItem);
        return OrderItemMapper.toDTO(saved);
    }
    

    public boolean deleteOrderItem(UUID id) {
        if (orderItemRepository.existsById(id)) {
            orderItemRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

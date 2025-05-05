package com.chainlink.mappers;

import com.chainlink.dtos.OrderDTO;
import com.chainlink.models.Order;

public class OrderMapper {

    public static OrderDTO toDTO(Order order) {
        return new OrderDTO(
            order.getId(),
            order.getOrderNumber(),
            order.getCustomerId(),
            order.getTotalAmount(),
            order.getOrderDate(),
            order.getStatus(),
            order.getShippingAddress()
        );
    }

    public static Order toEntity(OrderDTO dto) {
        return new Order(
            dto.getId(),
            dto.getOrderNumber(),
            dto.getCustomerId(),
            dto.getTotalAmount(),
            dto.getOrderDate(),
            dto.getStatus(),
            dto.getShippingAddress()
        );
    }
}

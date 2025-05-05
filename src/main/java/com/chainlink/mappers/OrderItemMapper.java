package com.chainlink.mappers;

import com.chainlink.dtos.OrderItemDTO;
import com.chainlink.models.Order;
import com.chainlink.models.OrderItem;
import com.chainlink.models.Product;

public class OrderItemMapper {

    public static OrderItemDTO toDTO(OrderItem orderItem) {
        return new OrderItemDTO(
            orderItem.getId(),
            orderItem.getOrder().getId(),
            orderItem.getProduct().getId(),
            orderItem.getQuantity(),
            orderItem.getPrice(),
            orderItem.getTotal()
        );
    }

    public static OrderItem toEntity(OrderItemDTO dto, Product product, Order order) {
        return new OrderItem(
            dto.getId(),
            order,
            product,
            dto.getQuantity(),
            dto.getPrice()
        );
    }
}

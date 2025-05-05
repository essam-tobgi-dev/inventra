package com.chainlink.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chainlink.models.Order;
import com.chainlink.models.OrderItem;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID>{
    List<OrderItem> findAllByOrder(Order order);
}

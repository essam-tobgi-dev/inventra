package com.chainlink.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.chainlink.enums.OrderStatus;
import com.chainlink.validations.Create;
import com.chainlink.validations.Update;

import jakarta.validation.constraints.Null;

public class OrderDTO {

    @Null(groups = Create.class)
    @Null(groups = Update.class)
    private UUID id;
    private Long orderNumber;
    private UUID customerId;
    private float totalAmount;
    private LocalDate orderDate;
    private OrderStatus status;
    private String shippingAddress;

    public OrderDTO() {}

    public OrderDTO(UUID id, Long orderNumber, UUID customerId, float totalAmount, LocalDate orderDate, OrderStatus status, String shippingAddress) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.status = status;
        this.shippingAddress = shippingAddress;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public UUID getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public float getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

}

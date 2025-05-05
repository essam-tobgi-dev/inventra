package com.chainlink.models;

import java.time.LocalDate;
import java.util.UUID;

import com.chainlink.enums.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @Column(name = "order_number", unique = true, nullable = false)
    private long orderNumber;

    @NotNull
    private UUID customerId;

    @NotNull
    private float totalAmount;

    @NotNull
    private LocalDate orderDate;

    @NotNull
    private OrderStatus status;

    @NotNull
    private String shippingAddress;

    public Order() { }

    public Order(UUID id, long orderNumber, UUID customerId, float totalAmount, LocalDate orderDate, OrderStatus status, String shippingAddress) {
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

    public long getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
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

package com.chainlink.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import com.chainlink.validations.Create;
import com.chainlink.validations.Update;

import jakarta.validation.constraints.Null;

public class OrderItemDTO {

    @Null(groups = Create.class)
    @Null(groups = Update.class)
    private UUID id;
    private UUID orderId;
    private UUID productId;
    private int quantity;
    private float price;
    private BigDecimal total;

    public OrderItemDTO() {}

    public OrderItemDTO(UUID id, UUID orderId, UUID productId, int quantity, float price, BigDecimal total) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public BigDecimal getTotal() {
        return total;
    }
}

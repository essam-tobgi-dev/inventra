package com.chainlink.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.chainlink.validations.Create;
import com.chainlink.validations.Update;

import jakarta.validation.constraints.Null;

public class InventoryDTO {

    @Null(groups = Create.class)
    @Null(groups = Update.class)
    private UUID id;
    private int quantity;
    private UUID productId;
    private UUID warehouseId;
    private LocalDate lastUpdatedAt;

    public InventoryDTO() {}

    public InventoryDTO(UUID id, int quantity, UUID productId, UUID warehouseId, LocalDate lastUpdatedAt) {
        this.id = id;
        this.quantity = quantity;
        this.productId = productId;
        this.warehouseId = warehouseId;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }

    public LocalDate getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDate lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

}

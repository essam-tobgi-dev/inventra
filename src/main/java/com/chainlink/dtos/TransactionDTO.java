package com.chainlink.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.chainlink.enums.TransactionType;
import com.chainlink.validations.Create;
import com.chainlink.validations.Update;

import jakarta.validation.constraints.Null;

public class TransactionDTO {

    @Null(groups = Create.class)
    @Null(groups = Update.class)
    private UUID id;
    private UUID inventoryId;
    private TransactionType type;
    private int quantity;
    private LocalDate timestamp;
    private String remarks;

    public TransactionDTO() {}

    public TransactionDTO(UUID id, UUID inventoryId, TransactionType type, int quantity, LocalDate timestamp, String remarks) {
        this.id = id;
        this.inventoryId = inventoryId;
        this.type = type;
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.remarks = remarks;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getInventory() {
        return inventoryId;
    }

    public void setInventory(UUID inventoryId) {
        this.inventoryId = inventoryId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}

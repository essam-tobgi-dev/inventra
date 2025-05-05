package com.chainlink.models;

import java.time.LocalDate;
import java.util.UUID;

import com.chainlink.enums.TransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="inventory_id", nullable=false)
    private Inventory inventory;

    @NotNull
    private TransactionType type;

    @NotNull
    private int quantity;

    @NotNull
    private LocalDate timestamp;

    @NotNull
    private String remarks;

    public Transaction() { }

    public Transaction(UUID id, Inventory inventory, TransactionType type, int quantity, LocalDate timestamp, String remarks) {
        this.id = id;
        this.inventory = inventory;
        this.type = type;
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.remarks = remarks;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Inventory getInventory(){
        return this.inventory;
    }

    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }

    public TransactionType getType() {
        return this.type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}

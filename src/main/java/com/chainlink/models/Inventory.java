package com.chainlink.models;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @NotNull
    private int quantity;

    @NotNull
    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

    @NotNull
    @ManyToOne
    @JoinColumn(name="warehouse_id", nullable=false)
    private Warehouse warehouse;

    @NotNull
    private LocalDate lastUpdatedAt;

    public Inventory() {}

    public Inventory(UUID id, int quantity, Product product, Warehouse warehouse, LocalDate lastUpdatedAt) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
        this.warehouse = warehouse;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Warehouse getWarehouse() {
        return this.warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public LocalDate getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDate lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

}

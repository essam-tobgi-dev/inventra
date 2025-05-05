package com.chainlink.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.chainlink.validations.Create;
import com.chainlink.validations.Update;

import jakarta.validation.constraints.Null;

public class ProductDTO {

    @Null(groups = Create.class)
    @Null(groups = Update.class)
    private UUID id;
    private String name;
    private String description;
    private String category;
    private float price;
    private String sku;
    private String manufacturer;
    private String supplier;
    private String barcode;
    private LocalDate createdAt;

    public ProductDTO() {}

    public ProductDTO(UUID id, String name, String description, String category, float price, String sku,
                      String manufacturer, String supplier, String barcode, LocalDate createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.sku = sku;
        this.manufacturer = manufacturer;
        this.supplier = supplier;
        this.barcode = barcode;
        this.createdAt = createdAt;
    }

    // Getters & Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}

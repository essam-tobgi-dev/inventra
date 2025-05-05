package com.chainlink.models;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String category;

    @NotNull
    private float price;

    @NotNull
    private String sku;

    @NotNull
    private String manufacturer;

    @NotNull
    private String supplier;

    @NotNull
    @Column(length=50)
    private String barcode;

    @NotNull
    private LocalDate createdAt;

    public Product(){ }


    public Product(UUID id, String name, String description, String category, float price, String sku, String manufacturer, String supplier, String barcode, LocalDate createdAt) {
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

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSku() {
        return this.sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSupplier() {
        return this.supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public LocalDate getCreatedAt(){
        return this.createdAt;
    }

    public void setCreatedAt(LocalDate createdAt){
        this.createdAt = createdAt;
    }

}

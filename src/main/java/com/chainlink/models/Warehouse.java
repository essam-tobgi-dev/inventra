package com.chainlink.models;

import java.util.UUID;

import com.chainlink.enums.WarehouseStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private String city;

    @NotNull
    private String address;

    @NotNull
    private int capacity;

    @NotNull
    private WarehouseStatus status;

    public Warehouse() { }

    public Warehouse(UUID id, String name, String city, String address, int capacity, WarehouseStatus status) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.capacity = capacity;
        this.status = status;
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

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public WarehouseStatus getStatus() {
        return this.status;
    }

    public void setStatus(WarehouseStatus status) {
        this.status = status;
    }

}

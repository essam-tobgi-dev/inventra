package com.chainlink.dtos;

import java.util.UUID;

import com.chainlink.enums.WarehouseStatus;
import com.chainlink.validations.Create;
import com.chainlink.validations.Update;

import jakarta.validation.constraints.Null;

public class WarehouseDTO {

    @Null(groups = Create.class)
    @Null(groups = Update.class)
    private UUID id;
    private String name;
    private String city;
    private String address;
    private int capacity;
    private WarehouseStatus status;

    public WarehouseDTO() {}

    public WarehouseDTO(UUID id, String name, String city, String address, int capacity, WarehouseStatus status) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.capacity = capacity;
        this.status = status;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public WarehouseStatus getStatus() {
        return status;
    }

    public void setStatus(WarehouseStatus status) {
        this.status = status;
    }

}

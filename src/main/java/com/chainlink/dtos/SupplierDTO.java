package com.chainlink.dtos;

import java.util.UUID;

import com.chainlink.validations.Create;
import com.chainlink.validations.Update;

import jakarta.validation.constraints.Null;

public class SupplierDTO {

    @Null(groups = Create.class)
    @Null(groups = Update.class)
    private UUID id;
    private String name;
    private String contactData;
    private String address;

    public SupplierDTO() {}

    public SupplierDTO(UUID id, String name, String contactData, String address) {
        this.id = id;
        this.name = name;
        this.contactData = contactData;
        this.address = address;
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

    public String getContactData() {
        return contactData;
    }

    public void setContactData(String contactData) {
        this.contactData = contactData;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

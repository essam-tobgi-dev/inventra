package com.chainlink.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private String contactData;

    @NotNull
    private String address;
    
    public Supplier() {}

    public Supplier(UUID id, String name, String contactData, String address) {
        this.id = id;
        this.name = name;
        this.contactData = contactData;
        this.address = address;
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

    public String getContactData() {
        return this.contactData;
    }

    public void setContactData(String contactData) {
        this.contactData = contactData;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

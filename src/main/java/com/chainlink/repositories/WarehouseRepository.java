package com.chainlink.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chainlink.models.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, UUID>{
}

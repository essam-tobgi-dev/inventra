package com.chainlink.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chainlink.models.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, UUID>{
}

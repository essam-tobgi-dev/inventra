package com.chainlink.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chainlink.models.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>{
    Page<Product> findByNameContaining(String name, Pageable pageable);
}

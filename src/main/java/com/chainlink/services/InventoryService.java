package com.chainlink.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chainlink.dtos.InventoryDTO;
import com.chainlink.exceptions.InventoryNotFoundException;
import com.chainlink.mappers.InventoryMapper;
import com.chainlink.models.Inventory;
import com.chainlink.models.Product;
import com.chainlink.models.Warehouse;
import com.chainlink.repositories.InventoryRepository;
import com.chainlink.repositories.ProductRepository;
import com.chainlink.repositories.WarehouseRepository;


@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public InventoryService(
        InventoryRepository inventoryRepository,
        ProductRepository productRepository,
        WarehouseRepository warehouseRepository
    ){
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
    }

    public Page<InventoryDTO> getAllProducts(int pageIndex, int pageSize){
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return inventoryRepository
            .findAll(pageable)
            .map(InventoryMapper::toDTO);
    }

    public InventoryDTO getProductById(UUID id){
        return InventoryMapper.toDTO(inventoryRepository.findById(id).orElseThrow());
    }

    public InventoryDTO createProduct(InventoryDTO inventoryDTO){
        Product product = productRepository.findById(inventoryDTO.getProductId()).orElseThrow();
        Warehouse warehouse = warehouseRepository.findById(inventoryDTO.getWarehouseId()).orElseThrow();
        return InventoryMapper.toDTO(inventoryRepository.save(InventoryMapper.toEntity(inventoryDTO, product, warehouse)));
    }

    public InventoryDTO updateInventoryDTO(UUID id, InventoryDTO newInventory) throws InventoryNotFoundException {
        Inventory existingInventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory with ID " + id + " not found"));

        if (newInventory.getId() != null && !newInventory.getId().equals(id)) {
            throw new IllegalArgumentException("Inventory ID in request body does not match URL path ID.");
        }

        Product product = productRepository.findById(newInventory.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + newInventory.getProductId() + " not found"));

        Warehouse warehouse = warehouseRepository.findById(newInventory.getWarehouseId())
                .orElseThrow(() -> new IllegalArgumentException("Warehouse with ID " + newInventory.getWarehouseId() + " not found"));

        existingInventory.setQuantity(newInventory.getQuantity());
        existingInventory.setProduct(product);
        existingInventory.setWarehouse(warehouse);
        existingInventory.setLastUpdatedAt(newInventory.getLastUpdatedAt());

        Inventory updatedInventory = inventoryRepository.save(existingInventory);
        return InventoryMapper.toDTO(updatedInventory);
    }

    public void removeProduct(UUID id){
        inventoryRepository.deleteById(id);
    }
}

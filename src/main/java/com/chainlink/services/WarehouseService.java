package com.chainlink.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.chainlink.dtos.WarehouseDTO;
import com.chainlink.mappers.WarehouseMapper;
import com.chainlink.models.Warehouse;
import com.chainlink.repositories.WarehouseRepository;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public WarehouseDTO createWarehouse(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = WarehouseMapper.toEntity(warehouseDTO);
        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        return WarehouseMapper.toDTO(savedWarehouse);
    }

    public List<WarehouseDTO> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        return warehouses.stream()
                         .map(WarehouseMapper::toDTO)
                         .toList();
    }


    public Optional<WarehouseDTO> getWarehouseById(UUID id) {
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(id);
        return warehouseOptional.map(WarehouseMapper::toDTO);
    }

    public Optional<WarehouseDTO> updateWarehouse(UUID id, WarehouseDTO warehouseDTO) {
        Optional<Warehouse> existingWarehouseOpt = warehouseRepository.findById(id);
        if (existingWarehouseOpt.isPresent()) {
            Warehouse existingWarehouse = existingWarehouseOpt.get();
            existingWarehouse.setName(warehouseDTO.getName());
            existingWarehouse.setCity(warehouseDTO.getCity());
            existingWarehouse.setAddress(warehouseDTO.getAddress());
            existingWarehouse.setCapacity(warehouseDTO.getCapacity());
            existingWarehouse.setStatus(warehouseDTO.getStatus());

            Warehouse updatedWarehouse = warehouseRepository.save(existingWarehouse);
            return Optional.of(WarehouseMapper.toDTO(updatedWarehouse));
        }
        return Optional.empty();
    }

    public boolean deleteWarehouse(UUID id) {
        Optional<Warehouse> warehouseOpt = warehouseRepository.findById(id);
        if (warehouseOpt.isPresent()) {
            warehouseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

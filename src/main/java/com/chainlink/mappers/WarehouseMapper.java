package com.chainlink.mappers;

import com.chainlink.dtos.WarehouseDTO;
import com.chainlink.models.Warehouse;

public class WarehouseMapper {

    public static WarehouseDTO toDTO(Warehouse warehouse) {
        return new WarehouseDTO(
            warehouse.getId(),
            warehouse.getName(),
            warehouse.getCity(),
            warehouse.getAddress(),
            warehouse.getCapacity(),
            warehouse.getStatus()
        );
    }

    public static Warehouse toEntity(WarehouseDTO dto) {
        return new Warehouse(
            dto.getId(),
            dto.getName(),
            dto.getCity(),
            dto.getAddress(),
            dto.getCapacity(),
            dto.getStatus()
        );
    }
}

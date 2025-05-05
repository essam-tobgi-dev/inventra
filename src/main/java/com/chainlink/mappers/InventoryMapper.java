package com.chainlink.mappers;

import com.chainlink.dtos.InventoryDTO;
import com.chainlink.models.Inventory;
import com.chainlink.models.Product;
import com.chainlink.models.Warehouse;

public class InventoryMapper {

    public static InventoryDTO toDTO(Inventory inventory) {
        if (inventory == null) {
            return null;
        }

        return new InventoryDTO(
            inventory.getId(),
            inventory.getQuantity(),
            inventory.getProduct() != null ? inventory.getProduct().getId() : null,
            inventory.getWarehouse() != null ? inventory.getWarehouse().getId() : null,
            inventory.getLastUpdatedAt()
        );
    }

    public static Inventory toEntity(InventoryDTO dto, Product product, Warehouse warehouse) {
        if (dto == null) {
            return null;
        }

        return new Inventory(
            dto.getId(),
            dto.getQuantity(),
            product,
            warehouse,
            dto.getLastUpdatedAt()
        );
    }
}

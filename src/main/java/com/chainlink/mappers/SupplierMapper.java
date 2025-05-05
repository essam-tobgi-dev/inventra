package com.chainlink.mappers;

import com.chainlink.dtos.SupplierDTO;
import com.chainlink.models.Supplier;

public class SupplierMapper {

    public static SupplierDTO toDTO(Supplier supplier) {
        return new SupplierDTO(
            supplier.getId(),
            supplier.getName(),
            supplier.getContactData(),
            supplier.getAddress()
        );
    }

    public static Supplier toEntity(SupplierDTO dto) {
        return new Supplier(
            dto.getId(),
            dto.getName(),
            dto.getContactData(),
            dto.getAddress()
        );
    }
}

package com.chainlink.mappers;

import com.chainlink.dtos.ProductDTO;
import com.chainlink.models.Product;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        if (product == null) return null;

        return new ProductDTO(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getCategory(),
            product.getPrice(),
            product.getSku(),
            product.getManufacturer(),
            product.getSupplier(),
            product.getBarcode(),
            product.getCreatedAt()
        );
    }

    public static Product toEntity(ProductDTO dto) {
        if (dto == null) return null;

        return new Product(
            dto.getId(),
            dto.getName(),
            dto.getDescription(),
            dto.getCategory(),
            dto.getPrice(),
            dto.getSku(),
            dto.getManufacturer(),
            dto.getSupplier(),
            dto.getBarcode(),
            dto.getCreatedAt()
        );
    }
}

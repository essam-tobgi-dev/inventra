package com.chainlink.mappers;

import com.chainlink.dtos.TransactionDTO;
import com.chainlink.models.Inventory;
import com.chainlink.models.Transaction;

public class TransactionMapper {

    public static TransactionDTO toDTO(Transaction transaction) {
        return new TransactionDTO(
            transaction.getId(),
            transaction.getInventory().getId(),
            transaction.getType(),
            transaction.getQuantity(),
            transaction.getTimestamp(),
            transaction.getRemarks()
        );
    }

    public static Transaction toEntity(TransactionDTO dto, Inventory inventory) {
        return new Transaction(
            dto.getId(),
            inventory,
            dto.getType(),
            dto.getQuantity(),
            dto.getTimestamp(),
            dto.getRemarks()
        );
    }
}

package com.chainlink.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chainlink.dtos.TransactionDTO;
import com.chainlink.mappers.TransactionMapper;
import com.chainlink.models.Inventory;
import com.chainlink.models.Transaction;
import com.chainlink.repositories.InventoryRepository;
import com.chainlink.repositories.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(TransactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<TransactionDTO> getTransactionById(UUID id) {
        return transactionRepository.findById(id)
                .map(TransactionMapper::toDTO);
    }

    public Optional<TransactionDTO> createTransaction(TransactionDTO dto) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findById(dto.getInventory());
        if (inventoryOpt.isEmpty()) return Optional.empty();

        Transaction transaction = TransactionMapper.toEntity(dto, inventoryOpt.get());
        Transaction saved = transactionRepository.save(transaction);
        return Optional.of(TransactionMapper.toDTO(saved));
    }

    public boolean deleteTransaction(UUID id) {
        if (!transactionRepository.existsById(id)) return false;
        transactionRepository.deleteById(id);
        return true;
    }
}

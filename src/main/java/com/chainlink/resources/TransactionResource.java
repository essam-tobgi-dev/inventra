package com.chainlink.resources;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chainlink.dtos.TransactionDTO;
import com.chainlink.services.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transactions", description = "Endpoints for managing product transactions")
public class TransactionResource {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    @Operation(summary = "Get all transactions", description = "Retrieve a list of all product transactions.")
    @ApiResponse(responseCode = "200", description = "Transactions retrieved successfully")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get transaction by ID", description = "Retrieve a transaction by its UUID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Transaction found"),
        @ApiResponse(responseCode = "404", description = "Transaction not found")
    })
    public ResponseEntity<TransactionDTO> getTransactionById(
        @PathVariable @Parameter(description = "Transaction UUID") UUID id) {
        return transactionService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create transaction", description = "Create a new transaction.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Transaction created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input or creation failed")
    })
    public ResponseEntity<TransactionDTO> createTransaction(
        @RequestBody @Parameter(description = "Transaction details") TransactionDTO dto) {
        return transactionService.createTransaction(dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete transaction", description = "Delete a transaction by UUID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Transaction deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Transaction not found")
    })
    public ResponseEntity<Void> deleteTransaction(
        @PathVariable @Parameter(description = "Transaction UUID") UUID id) {
        boolean deleted = transactionService.deleteTransaction(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

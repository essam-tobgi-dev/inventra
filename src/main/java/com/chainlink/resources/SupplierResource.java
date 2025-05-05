package com.chainlink.resources;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chainlink.dtos.SupplierDTO;
import com.chainlink.services.SupplierService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/suppliers")
@Tag(name = "Suppliers", description = "Endpoints for managing suppliers")
public class SupplierResource {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    @Operation(summary = "Get all suppliers", description = "Retrieve a list of all suppliers.")
    @ApiResponse(responseCode = "200", description = "Suppliers retrieved successfully")
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get supplier by ID", description = "Retrieve a specific supplier by its UUID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Supplier found"),
        @ApiResponse(responseCode = "404", description = "Supplier not found")
    })
    public ResponseEntity<SupplierDTO> getSupplierById(
        @PathVariable @Parameter(description = "Supplier UUID") UUID id) {
        return supplierService.getSupplierById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create supplier", description = "Create a new supplier.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Supplier created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<SupplierDTO> createSupplier(
        @RequestBody @Parameter(description = "Supplier data") SupplierDTO dto) {
        SupplierDTO created = supplierService.createSupplier(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update supplier", description = "Update an existing supplier by ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Supplier updated successfully"),
        @ApiResponse(responseCode = "404", description = "Supplier not found")
    })
    public ResponseEntity<SupplierDTO> updateSupplier(
        @PathVariable @Parameter(description = "Supplier UUID") UUID id,
        @RequestBody @Parameter(description = "Updated supplier data") SupplierDTO dto) {
        return supplierService.updateSupplier(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete supplier", description = "Delete a supplier by ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Supplier deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Supplier not found")
    })
    public ResponseEntity<Void> deleteSupplier(
        @PathVariable @Parameter(description = "Supplier UUID") UUID id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}

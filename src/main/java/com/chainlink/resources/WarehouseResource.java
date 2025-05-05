package com.chainlink.resources;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chainlink.dtos.WarehouseDTO;
import com.chainlink.services.WarehouseService;
import com.chainlink.validations.Create;
import com.chainlink.validations.Update;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/warehouses")
@Tag(name = "Warehouses", description = "Endpoints for managing warehouses")
public class WarehouseResource {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    @Operation(summary = "Get all warehouses", description = "Retrieve a list of all warehouses.")
    @ApiResponse(responseCode = "200", description = "Warehouses retrieved successfully")
    public ResponseEntity<List<WarehouseDTO>> getAllWarehouses() {
        List<WarehouseDTO> warehouses = warehouseService.getAllWarehouses();
        return ResponseEntity.ok(warehouses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get warehouse by ID", description = "Retrieve a warehouse by its UUID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Warehouse found"),
        @ApiResponse(responseCode = "404", description = "Warehouse not found")
    })
    public ResponseEntity<WarehouseDTO> getWarehouseById(
        @PathVariable @Parameter(description = "Warehouse UUID") UUID id) {
        WarehouseDTO warehouse = warehouseService.getWarehouseById(id).orElse(null);
        if (warehouse != null) {
            return ResponseEntity.ok(warehouse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping
    @Operation(summary = "Create warehouse", description = "Create a new warehouse.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Warehouse created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<WarehouseDTO> createWarehouse(
        @RequestBody @Validated(Create.class)
        @Parameter(description = "Warehouse details to create") WarehouseDTO warehouseDTO
    ) {
        WarehouseDTO createdWarehouse = warehouseService.createWarehouse(warehouseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWarehouse);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update warehouse", description = "Update an existing warehouse by UUID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Warehouse updated successfully"),
        @ApiResponse(responseCode = "404", description = "Warehouse not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<WarehouseDTO> updateWarehouse(
        @PathVariable @Parameter(description = "Warehouse UUID") UUID id,
        @RequestBody @Validated(Update.class)
        @Parameter(description = "Updated warehouse details") WarehouseDTO warehouseDTO
    ) {
        WarehouseDTO updatedWarehouse = warehouseService.updateWarehouse(id, warehouseDTO).orElse(null);
        if (updatedWarehouse != null) {
            return ResponseEntity.ok(updatedWarehouse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete warehouse", description = "Delete a warehouse by its UUID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Warehouse deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Warehouse not found")
    })
    public ResponseEntity<Void> deleteWarehouse(
        @PathVariable @Parameter(description = "Warehouse UUID") UUID id) {
        boolean isDeleted = warehouseService.deleteWarehouse(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

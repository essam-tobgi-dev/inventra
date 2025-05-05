package com.chainlink.resources;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chainlink.dtos.InventoryDTO;
import com.chainlink.exceptions.InventoryNotFoundException;
import com.chainlink.services.InventoryService;
import com.chainlink.validations.Create;
import com.chainlink.validations.Update;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/inventories")
@Tag(name = "Inventory", description = "APIs for managing inventory items")
public class InventoryResource {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    @Operation(summary = "Get all inventories", description = "Fetches a paginated list of all inventory items.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved inventory list")
    })
    public ResponseEntity<Page<InventoryDTO>> getAllInventories(
            @RequestParam(defaultValue = "0") @Parameter(description = "Page number (0-based)") int pageIndex,
            @RequestParam(defaultValue = "10") @Parameter(description = "Number of items per page") int pageSize) {
        return ResponseEntity.ok(inventoryService.getAllProducts(pageIndex, pageSize));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get inventory by ID", description = "Retrieves a specific inventory item by its unique ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventory found"),
        @ApiResponse(responseCode = "404", description = "Inventory not found")
    })
    public ResponseEntity<InventoryDTO> getInventoryById(
            @PathVariable @Parameter(description = "Unique identifier of the inventory item") UUID id) {
        return ResponseEntity.ok(inventoryService.getProductById(id));
    }

    @PostMapping
    @Operation(summary = "Create inventory", description = "Creates a new inventory item.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventory created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<InventoryDTO> createInventory(
        @RequestBody @Validated(Create.class) @Parameter(description = "Inventory object to be created") InventoryDTO inventoryDTO
    ) {
        return ResponseEntity.ok(inventoryService.createProduct(inventoryDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update inventory", description = "Updates an existing inventory item by its ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventory updated successfully"),
        @ApiResponse(responseCode = "404", description = "Inventory not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<InventoryDTO> updateInventory(
            @PathVariable @Parameter(description = "Unique identifier of the inventory item") UUID id,
            @RequestBody @Validated(Update.class) @Parameter(description = "Updated inventory object") InventoryDTO inventoryDTO
    ) throws InventoryNotFoundException {
        return ResponseEntity.ok(inventoryService.updateInventoryDTO(id, inventoryDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete inventory", description = "Deletes an inventory item by its ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Inventory deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Inventory not found")
    })
    public ResponseEntity<Void> deleteInventory(
            @PathVariable @Parameter(description = "Unique identifier of the inventory item") UUID id) {
        inventoryService.removeProduct(id);
        return ResponseEntity.noContent().build();
    }
}

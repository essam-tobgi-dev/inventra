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

import com.chainlink.dtos.OrderItemDTO;
import com.chainlink.services.OrderItemService;
import com.chainlink.validations.Create;
import com.chainlink.validations.Update;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/order-items")
@Tag(name = "Order Items", description = "Operations related to order item management")
public class OrderItemResource {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    @Operation(summary = "Get all order items", description = "Retrieves a list of all order items.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of order items")
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItems() {
        List<OrderItemDTO> orderItems = orderItemService.getAllOrderItems();
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order item by ID", description = "Retrieves a specific order item by its ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order item found"),
        @ApiResponse(responseCode = "404", description = "Order item not found")
    })
    public ResponseEntity<OrderItemDTO> getOrderItemById(
            @PathVariable @Parameter(description = "Unique identifier of the order item") UUID id) {
        OrderItemDTO orderItem = orderItemService.getOrderItemById(id);
        if (orderItem != null) {
            return new ResponseEntity<>(orderItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Create new order item", description = "Creates a new order item.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Order item created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<OrderItemDTO> createOrderItem(
        @RequestBody @Validated(Create.class)
        @Parameter(description = "Order item object to be created") OrderItemDTO orderItemDTO
    ) {
        OrderItemDTO createdOrderItem = orderItemService.createOrderItem(orderItemDTO);
        return new ResponseEntity<>(createdOrderItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update existing order item", description = "Updates an existing order item based on ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order item updated successfully"),
        @ApiResponse(responseCode = "404", description = "Order item not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<OrderItemDTO> updateOrderItem(
            @PathVariable @Parameter(description = "Unique identifier of the order item") UUID id,
            @RequestBody @Validated(Update.class)
            @Parameter(description = "Updated order item object") OrderItemDTO orderItemDTO) {

        OrderItemDTO updatedOrderItem = orderItemService.updateOrderItem(id, orderItemDTO);
        if (updatedOrderItem != null) {
            return new ResponseEntity<>(updatedOrderItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order item", description = "Deletes an order item by its ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Order item deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Order item not found")
    })
    public ResponseEntity<Void> deleteOrderItem(
            @PathVariable @Parameter(description = "Unique identifier of the order item") UUID id) {
        boolean isDeleted = orderItemService.deleteOrderItem(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

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

import com.chainlink.dtos.OrderDTO;
import com.chainlink.exceptions.OutOfStockException;
import com.chainlink.services.OrderService;
import com.chainlink.validations.Create;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/orders")
@Tag(name = "Orders", description = "Operations related to orders")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @GetMapping
    @Operation(summary = "Get all orders", description = "Retrieves a list of all orders.")
    @ApiResponse(responseCode = "200", description = "List of orders retrieved successfully")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID", description = "Retrieves an order by its unique ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order found"),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<OrderDTO> getOrderById(
        @PathVariable @Parameter(description = "ID of the order to retrieve") UUID id) {
        OrderDTO order = orderService.getOrderById(id);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Create a new order", description = "Creates a new order. Throws OutOfStockException if any item is unavailable.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Order created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "409", description = "One or more products are out of stock")
    })
    public ResponseEntity<OrderDTO> createOrder(
        @RequestBody @Validated(Create.class)
        @Parameter(description = "Order data to create") OrderDTO orderDTO
    ) throws OutOfStockException {
        OrderDTO createdOrder = orderService.createOrder(orderDTO);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing order", description = "Updates an existing order by ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order updated successfully"),
        @ApiResponse(responseCode = "404", description = "Order not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<OrderDTO> updateOrder(
        @PathVariable @Parameter(description = "ID of the order to update") UUID id,
        @RequestBody @Validated(Create.class)
        @Parameter(description = "Updated order data") OrderDTO orderDTO) {

        OrderDTO updatedOrder = orderService.updateOrder(id, orderDTO);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an order", description = "Deletes an order by its unique ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Order deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<Void> deleteOrder(
        @PathVariable @Parameter(description = "ID of the order to delete") UUID id) {
        boolean isDeleted = orderService.deleteOrder(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

package com.chainlink.resources;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chainlink.dtos.ProductDTO;
import com.chainlink.exceptions.ProductNotFoundException;
import com.chainlink.services.ProductService;
import com.chainlink.validations.Create;
import com.chainlink.validations.Update;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Products", description = "Endpoints for managing products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    @Operation(summary = "Get all products", description = "Retrieves paginated list of all products.")
    @ApiResponse(responseCode = "200", description = "Products retrieved successfully")
    public ResponseEntity<Page<ProductDTO>> getAllProducts(
        @RequestParam(defaultValue = "0") @Parameter(description = "Page index") int pageIndex,
        @RequestParam(defaultValue = "10") @Parameter(description = "Page size") int pageSize
    ) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(productService.getAllProducts(pageIndex, pageSize));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Fetch a product using its UUID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Product found"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<ProductDTO> getProductById(
        @PathVariable @Parameter(description = "Product UUID") UUID id) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(productService.getProductById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Search product by name", description = "Search for products containing the specified name.")
    @ApiResponse(responseCode = "200", description = "Search results returned")
    public ResponseEntity<Page<ProductDTO>> searchProductByName(
        @RequestParam @Parameter(description = "Search term for product name") String name,
        @RequestParam(defaultValue = "0") @Parameter(description = "Page index") int pageIndex,
        @RequestParam(defaultValue = "10") @Parameter(description = "Page size") int pageSize
    ) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(productService.searchByName(name, pageIndex, pageSize));
    }

    @PostMapping("/")
    @Operation(summary = "Create new product", description = "Create a new product with the given data.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Product created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    public ResponseEntity<ProductDTO> createProduct(
        @RequestBody @Validated(Create.class)
        @Parameter(description = "Product data for creation") ProductDTO product
    ) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(productService.createProduct(product));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update product", description = "Update an existing product by ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Product updated successfully"),
        @ApiResponse(responseCode = "404", description = "Product not found"),
        @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    public ResponseEntity<ProductDTO> updateProduct(
        @PathVariable @Parameter(description = "Product UUID") UUID id,
        @RequestBody @Validated(Update.class)
        @Parameter(description = "Updated product data") ProductDTO newProduct
    ) throws ProductNotFoundException {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(productService.updateProductDTO(id, newProduct));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product", description = "Delete a product by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<Void> deleteProduct(
        @PathVariable @Parameter(description = "Product UUID") UUID id) {
        productService.removeProduct(id);
        return ResponseEntity.noContent().build();
    }
}

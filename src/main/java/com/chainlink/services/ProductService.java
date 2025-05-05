package com.chainlink.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chainlink.dtos.ProductDTO;
import com.chainlink.exceptions.ProductNotFoundException;
import com.chainlink.mappers.ProductMapper;
import com.chainlink.models.Product;
import com.chainlink.repositories.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Page<ProductDTO> getAllProducts(int pageIndex, int pageSize){
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return productRepository
            .findAll(pageable)
            .map(ProductMapper::toDTO);
    }

    public ProductDTO getProductById(UUID id){
        return ProductMapper.toDTO(productRepository.findById(id).orElseThrow());
    }

    public Page<ProductDTO> searchByName(String name, int pageIndex, int pageSize){
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return productRepository
            .findByNameContaining(name, pageable)
            .map(ProductMapper::toDTO);
    }

    public ProductDTO createProduct(ProductDTO productDTO){
        return ProductMapper.toDTO(productRepository.save(ProductMapper.toEntity(productDTO)));
    }

    public ProductDTO updateProductDTO(UUID id, ProductDTO newProduct) throws ProductNotFoundException {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + newProduct.getId() + " not found"));
    
        existingProduct.setName(newProduct.getName());
        existingProduct.setDescription(newProduct.getDescription());
        existingProduct.setCategory(newProduct.getCategory());
        existingProduct.setPrice(newProduct.getPrice());
        existingProduct.setSku(newProduct.getSku());
        existingProduct.setManufacturer(newProduct.getManufacturer());
        existingProduct.setSupplier(newProduct.getSupplier());
        existingProduct.setBarcode(newProduct.getBarcode());
        existingProduct.setCreatedAt(newProduct.getCreatedAt());
    
        Product updatedProduct = productRepository.save(existingProduct);
    
        return ProductMapper.toDTO(updatedProduct);
    }

    public void removeProduct(UUID id){
        productRepository.deleteById(id);
    }
}

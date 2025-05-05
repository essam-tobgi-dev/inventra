package com.chainlink.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chainlink.dtos.SupplierDTO;
import com.chainlink.mappers.SupplierMapper;
import com.chainlink.models.Supplier;
import com.chainlink.repositories.SupplierRepository;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<SupplierDTO> getAllSuppliers() {
        return supplierRepository.findAll().stream()
                .map(SupplierMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<SupplierDTO> getSupplierById(UUID id) {
        return supplierRepository.findById(id)
                .map(SupplierMapper::toDTO);
    }

    public SupplierDTO createSupplier(SupplierDTO dto) {
        Supplier supplier = SupplierMapper.toEntity(dto);
        Supplier saved = supplierRepository.save(supplier);
        return SupplierMapper.toDTO(saved);
    }

    public Optional<SupplierDTO> updateSupplier(UUID id, SupplierDTO dto) {
        return supplierRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setContactData(dto.getContactData());
            existing.setAddress(dto.getAddress());
            Supplier updated = supplierRepository.save(existing);
            return SupplierMapper.toDTO(updated);
        });
    }

    public void deleteSupplier(UUID id) {
        supplierRepository.deleteById(id);
    }
}

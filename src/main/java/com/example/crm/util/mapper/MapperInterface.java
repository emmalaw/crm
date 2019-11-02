package com.example.crm.util.mapper;

/**
 * MapperInterface
 */
public interface MapperInterface<E, DTO> {

    E toEntity(DTO dto);
    DTO toDTO(E entity);
}
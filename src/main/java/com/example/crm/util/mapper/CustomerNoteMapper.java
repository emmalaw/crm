package com.example.crm.util.mapper;

import com.example.crm.domain.dto.out.CustomerNote;
import com.example.crm.domain.entity.CustomerNoteEntity;

import fr.xebia.extras.selma.Field;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;

/**
 * CustomerMapper
 */
@Mapper(withIoC = IoC.SPRING)
public interface CustomerNoteMapper extends MapperInterface<CustomerNoteEntity, CustomerNote> {
    @Maps(
        withIgnoreFields = {"customer", "customerId"}
    )
    public CustomerNoteEntity toEntity(CustomerNote customerNote);
    @Maps(
        withCustomFields = {
            @Field({"customerNote.customerId" ,"customerNoteEntity.customer.customerId"})
        }
    )
    public CustomerNote toDTO(CustomerNoteEntity entity);
}
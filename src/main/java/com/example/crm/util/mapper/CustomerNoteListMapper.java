package com.example.crm.util.mapper;

import java.util.List;

import com.example.crm.domain.dto.out.CustomerNote;
import com.example.crm.domain.entity.CustomerNoteEntity;

import fr.xebia.extras.selma.CollectionMappingStrategy;
import fr.xebia.extras.selma.Field;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;


/**
 * CustomerMapper
 */
@Mapper(
    withCollectionStrategy = CollectionMappingStrategy.ALLOW_GETTER,
    withIoC = IoC.SPRING,
    //withIgnoreFields = {"customerNote.customer" ,"customerNoteEntity.customer"}
    withCustomFields = {
        @Field({"customerNote.customerId" ,"customerNoteEntity.customer.customerId"})
    }
)
public interface CustomerNoteListMapper extends MapperInterface<List<CustomerNoteEntity>, List<CustomerNote>> {

    public CustomerNoteEntity toEntity(CustomerNote customerNote);
    
    public CustomerNote toDTO(CustomerNoteEntity entity);
}
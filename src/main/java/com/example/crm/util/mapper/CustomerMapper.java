package com.example.crm.util.mapper;

import com.example.crm.domain.dto.out.Customer;
import com.example.crm.domain.entity.CustomerEntity;

import fr.xebia.extras.selma.Field;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;
/**
 * CustomerMapper
 */
@Mapper(withIoC = IoC.SPRING)
public interface CustomerMapper extends MapperInterface<CustomerEntity, Customer> {
    @Maps(
        withIgnoreFields = {"customer", "customerId"}
    )
    public CustomerEntity toEntity(Customer customer);
    @Maps(
        withCustomFields = {
            @Field({"customerNote.customerId" ,"customerNoteEntity.customer.customerId"})
        }
    )
    public Customer toDTO(CustomerEntity entity);
}
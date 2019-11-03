package com.example.crm.util.mapper;

import static org.junit.Assert.assertEquals;

import com.example.crm.domain.dto.out.Customer;
import com.example.crm.domain.dto.out.CustomerNote;
import com.example.crm.domain.entity.CustomerEntity;
import com.example.crm.domain.entity.CustomerNoteEntity;
import com.example.crm.util.mapper.CustomerMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * CustomerNoteMapperTest
 */

@RunWith( SpringRunner.class )
@SpringBootTest 
//@RunWith(MockitoJUnitRunner.class)
public class CustomerNoteMapperTest {
    /* @InjectMocks */
    @Autowired
    CustomerMapper customerMapper ;
    @Autowired
    CustomerNoteMapper customerNoteMapper ;
    @Autowired
    CustomerNoteListMapper customerNoteListMapper ;
 

    @Test
    public void testToDTO() {
        CustomerEntity customerEntity = new CustomerEntity();
        Date date = new Date();
        customerEntity.setCreateOn(date);
        customerEntity.setCustomerId(242352L);
        customerEntity.setNameFirst("First Name");
        customerEntity.setNameLast("Last Name");
        customerEntity.setNameMiddle("Middle Name");
        customerEntity.setPhone("23423-234");
        List<CustomerNoteEntity> notes = new ArrayList<CustomerNoteEntity>(1);
        customerEntity.setNotes(notes);
        CustomerNoteEntity customerNoteEntity = new CustomerNoteEntity();
        customerNoteEntity.setCustomer(customerEntity);
        customerNoteEntity.setNote("Hello World!");
        customerNoteEntity.setCustomerNoteId(1L);

        customerEntity.getNotes().add(customerNoteEntity);
       
        CustomerNote dto = customerNoteMapper.toDTO(customerNoteEntity);
        assertEquals(dto.getCustomerId(), customerNoteEntity.getCustomer().getCustomerId());
        assertEquals(dto.getNote(), customerNoteEntity.getNote());
    }
}
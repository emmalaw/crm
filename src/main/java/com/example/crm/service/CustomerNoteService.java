package com.example.crm.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.crm.domain.dto.in.NoteInput;
import com.example.crm.domain.dto.out.CustomerNote;
import com.example.crm.domain.entity.CustomerEntity;
import com.example.crm.domain.entity.CustomerNoteEntity;
import com.example.crm.repository.CustomerNoteRepository;
import com.example.crm.repository.CustomerRepository;
import com.example.crm.util.exception.CustomerNotFoundException;
import com.example.crm.util.exception.CustomerNoteNotFoundException;
import com.example.crm.util.exception.VersionNotFoundException;
import com.example.crm.util.mapper.CustomerMapper;
import com.example.crm.util.mapper.CustomerNoteMapper;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Service
@Component
public class CustomerNoteService {
    @Autowired
    CustomerNoteRepository customerNoteRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerNoteMapper customerNoteMapper;

    @Autowired
    CustomerMapper customerMapper;


    /**
     * 
     * @param customerNoteId
     * @return CustomerNote object by custometNoteId
     * @throws CustomerNoteNotFoundException
     */
    public CustomerNote findById(Long customerNoteId) throws CustomerNoteNotFoundException {
        CustomerNoteEntity customerNoteEntity = findByNoteId(customerNoteId);
        return customerNoteMapper.toDTO(customerNoteEntity);
    }

    /**
     * 
     * @param customerId
     * @return List<CustomerNote> by customerId
     * @throws CustomerNoteNotFoundException
     */
    public List<CustomerNote> findByCustomerId(Long customerId) throws CustomerNoteNotFoundException {
        CustomerEntity customerEntity = findCustomerById(customerId);
        List<CustomerNoteEntity> customerNoteEntities = customerNoteRepository.findAllByCustomer(customerEntity);
        return Optional.ofNullable(customerNoteEntities)
                        .orElseThrow(() -> new CustomerNoteNotFoundException("Customer not found"))
                        .stream().map(customerNoteMapper::toDTO)
                        .collect(Collectors.toList());
    }

    /**
     * 
     * @return List<CustomerNote> for all customers in db
     * @throws CustomerNoteNotFoundException
     */
    public List<CustomerNote> getList() throws CustomerNoteNotFoundException {
        List<CustomerNoteEntity> customerEntityList = (List<CustomerNoteEntity>) customerNoteRepository.findAll();
        return customerEntityList.stream().map(customerNoteMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * Add a note of a customer
     * @param NoteInput
     * @return boolean 
     * @throws CustomerNotFoundException
     * @throws CustomerNoteNotFoundException
     */
    public boolean add(NoteInput note) throws CustomerNotFoundException, CustomerNoteNotFoundException {
        CustomerEntity customerEntity = findCustomerById(note.getCustomerId());
        CustomerNoteEntity customerNoteEntity = 
            CustomerNoteEntity.builder().note(note.getNote()).customer(customerEntity).build();
        return customerNoteRepository.save(customerNoteEntity) != null;
    }

    /**
     * update a note of a customer
     * @param NoteInput
     * @return boolean
     * @throws CustomerNotFoundException
     * @throws CustomerNoteNotFoundException
     * @throws VersionNotFoundException
     */
    public boolean save(NoteInput note)  throws CustomerNotFoundException, CustomerNoteNotFoundException
    , VersionNotFoundException {
        CustomerNoteEntity customerNoteEntity = getCustomerNoteEntity(note);
        customerNoteEntity.setNote(note.getNote());
        return customerNoteRepository.save(customerNoteEntity) != null;
    }
    /**
     * Delete a note of a customer
     * @param NoteInput note
     * @return boolean
     * @throws CustomerNotFoundException
     * @throws CustomerNoteNotFoundException
     * @throws VersionNotFoundException
     */
    public boolean delete(NoteInput note)  throws CustomerNotFoundException, CustomerNoteNotFoundException
    , VersionNotFoundException {
        CustomerNoteEntity customerNoteEntity = getCustomerNoteEntity(note);
        customerNoteRepository.delete(customerNoteEntity);
        return Boolean.TRUE;
    }
    // 
    /**
     * 
     * @param customerId
     * @return
     * @throws CustomerNoteNotFoundException
     */
    private CustomerEntity findCustomerById(Long customerId) throws CustomerNoteNotFoundException {
        Optional<CustomerEntity> customerOptional = customerRepository.findByCustomerId(customerId);
        CustomerEntity customerEntity = customerOptional.orElseThrow(() -> new CustomerNoteNotFoundException("Customer not found"));
        return customerEntity;
    }
    /**
     * 
     * @param customerNoteId
     * @return CustomerNoteEntity
     * @throws CustomerNoteNotFoundException
     */
    private CustomerNoteEntity findByNoteId(Long customerNoteId) throws CustomerNoteNotFoundException {
        Optional<CustomerNoteEntity> customerNoteOptional = customerNoteRepository.findByCustomerNoteId(customerNoteId);
        CustomerNoteEntity customerNoteEntity = customerNoteOptional.orElseThrow(() -> new CustomerNoteNotFoundException("Customer not found"));
         return customerNoteEntity;
    }
    /**
     * 
     * @param note
     * @return CustomerNoteEntity
     * @throws CustomerNoteNotFoundException
     * @throws VersionNotFoundException
     */
    private CustomerNoteEntity getCustomerNoteEntity(NoteInput note) throws CustomerNoteNotFoundException, VersionNotFoundException {
        CustomerNoteEntity customerNoteEntity = findByNoteId(note.getCustomerNoteId());
        if (customerNoteEntity.getCustomer().getCustomerId() != note.getCustomerId()) {
            throw new CustomerNoteNotFoundException("Version not found");
        }
        if (customerNoteEntity.getCustomerNoteId() != note.getCustomerNoteId()) {
            throw new CustomerNoteNotFoundException("Version not found");
        }
        if (customerNoteEntity.getVersion() != note.getVersion()) {
            throw new VersionNotFoundException("Record is outdated. Please try again with updated data.");
        }
        return customerNoteEntity;
    }
}
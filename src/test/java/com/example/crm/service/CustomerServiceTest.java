package com.example.crm.service;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.example.crm.domain.dto.in.CustomerStatusInput;
import com.example.crm.domain.entity.CustomerEntity;
import com.example.crm.repository.CustomerRepository;
import com.example.crm.util.exception.CustomerNotFoundException;
import com.example.crm.util.exception.InvalidCustomerStatusException;
import com.example.crm.util.exception.VersionNotFoundException;
import com.example.crm.util.mapper.CustomerMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CustomerMapper customerMapper;

    @Test
    public void testFindByCustomerId_returnDto_whenEntityFound() throws CustomerNotFoundException {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setVersion(1L);
        when(customerRepository.findByCustomerId(1L)).thenReturn(Optional.of(customerEntity));
        customerService.findByCustomerId(1L);
        verify(customerMapper, times(1)).toDTO(customerEntity); 
    }

    @Test(expected = CustomerNotFoundException.class)
    public void testFindByCustomerId_returnException_whenEntityNotFound() throws CustomerNotFoundException {
        when(customerRepository.findByCustomerId(1L)).thenReturn(Optional.empty());
        customerService.findByCustomerId(1L);
    }

    @Test(expected = InvalidCustomerStatusException.class)
    public void testSaveStatus_returnException_whenInvalidStatus() throws
    CustomerNotFoundException, VersionNotFoundException, InvalidCustomerStatusException {
        CustomerStatusInput status = CustomerStatusInput.builder().status('T').build();
        customerService.saveStatus(status);
    }

    @Test(expected = CustomerNotFoundException.class)
    public void testSaveStatus_returnException_whenInvalidCustomerId() throws
    CustomerNotFoundException, VersionNotFoundException, InvalidCustomerStatusException {
        when(customerRepository.findByCustomerId(1L)).thenReturn(Optional.empty());
        CustomerStatusInput status = CustomerStatusInput.builder().status('P').customerId(1L).build();
        customerService.saveStatus(status);
    }

    @Test(expected = VersionNotFoundException.class)
    public void testSaveStatus_returnException_whenInvalidVersion() throws
    CustomerNotFoundException, VersionNotFoundException, InvalidCustomerStatusException {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setVersion(1L);
        when(customerRepository.findByCustomerId(1L)).thenReturn(Optional.of(customerEntity));
        CustomerStatusInput status = CustomerStatusInput.builder().status('P').customerId(1L).version(0L).build();
        customerService.saveStatus(status);
    }

    @Test()
    public void testSaveStatus_returnTrue_whenInputValid() throws
    CustomerNotFoundException, VersionNotFoundException, InvalidCustomerStatusException {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setVersion(1L);
        when(customerRepository.findByCustomerId(1L)).thenReturn(Optional.of(customerEntity));
        CustomerStatusInput status = CustomerStatusInput.builder().status('P').customerId(1L).version(1L).build();
        customerService.saveStatus(status);
    }
}
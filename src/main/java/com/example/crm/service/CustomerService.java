package com.example.crm.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.crm.domain.dto.in.CustomerSearchInput;
import com.example.crm.domain.dto.in.CustomerStatusInput;
import com.example.crm.domain.dto.out.Customer;
import com.example.crm.domain.entity.CustomerEntity;
import com.example.crm.repository.CustomerRepository;
import com.example.crm.util.exception.CustomerNotFoundException;
import com.example.crm.util.exception.CustomerNoteNotFoundException;
import com.example.crm.util.exception.InvalidCustomerStatusException;
import com.example.crm.util.exception.VersionNotFoundException;
import com.example.crm.util.mapper.CustomerMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    /**
     * 
     * @param customerId
     * @return Customer
     * @throws CustomerNotFoundException
     */
    public Customer findByCustomerId(Long customerId) throws CustomerNotFoundException {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findByCustomerId(customerId);
        CustomerEntity customerEntity = customerEntityOptional.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return customerMapper.toDTO(customerEntity);
    }
    
    /**
     * 
     * @param searchInput
     * @return
     * @throws CustomerNotFoundException
     * @throws CustomerNoteNotFoundException
     */
    public List<Customer> getCustomerList(CustomerSearchInput searchInput)
            throws CustomerNotFoundException {
        Sort sort = Sort.by(searchInput.isAsc()? Sort.Direction.ASC :  Sort.Direction.DESC, 
                     Optional.ofNullable(searchInput.getOrderField()).orElse("nameLast"));

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
            .withMatcher("nameLast", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("nameFirst", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("nameMiddle", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("phone", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setNameFirst(searchInput.getSearchString());
        customerEntity.setNameLast(searchInput.getSearchString());
        customerEntity.setNameMiddle(searchInput.getSearchString());
        customerEntity.setPhone(searchInput.getSearchString());
        Example<CustomerEntity> example = Example.of(customerEntity, exampleMatcher);
                
        List<CustomerEntity> customerEntityList = (List<CustomerEntity>) customerRepository.findAll(example, sort);
        return Optional.ofNullable(customerEntityList)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"))
                .stream().map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * 
     * @param statusInput
     * @return boolean
     * @throws CustomerNotFoundException
     * @throws VersionNotFoundException
     * @throws InvalidCustomerStatusException
     */
    public boolean saveStatus(CustomerStatusInput statusInput)
            throws CustomerNotFoundException, VersionNotFoundException, InvalidCustomerStatusException {
        if (statusInput.getStatus() != 'P' && statusInput.getStatus() != 'C' &&  statusInput.getStatus() != 'I') {
            throw new InvalidCustomerStatusException();
        }
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findByCustomerId(statusInput.getCustomerId());
        CustomerEntity customerEntity = customerEntityOptional.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        if (customerEntity.getVersion() != statusInput.getVersion()) {
            throw new VersionNotFoundException("Version is outdated. Please update and try again");
        }
        customerEntity.setStatus(statusInput.getStatus());
        return customerRepository.save(customerEntity) != null;
    }
}
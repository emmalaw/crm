package com.example.crm.repository;

import java.util.List;
import java.util.Optional;

import com.example.crm.domain.entity.CustomerEntity;
import com.example.crm.domain.entity.CustomerNoteEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CustomerNoteRepository extends CrudRepository<CustomerNoteEntity, Integer> {
    List<CustomerNoteEntity> findAllByCustomer(CustomerEntity customer);
    Optional<CustomerNoteEntity> findByCustomerNoteId(Long customerNoteId);
}
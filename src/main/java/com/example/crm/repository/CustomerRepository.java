package com.example.crm.repository;

import java.util.Optional;

import com.example.crm.domain.entity.CustomerEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer>, QueryByExampleExecutor<CustomerEntity>  {
    

    Optional<CustomerEntity> findByCustomerId(Long customerId);

}
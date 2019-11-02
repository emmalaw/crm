package com.example.crm.domain.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EditableCustomer
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerStatusInput {

    private Long customerId ;
    private char status;
    private Long version;

}
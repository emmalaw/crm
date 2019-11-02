package com.example.crm.domain.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SearchCustomerInput
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSearchInput {

    String orderField;
    boolean isAsc;
    String searchString;
}
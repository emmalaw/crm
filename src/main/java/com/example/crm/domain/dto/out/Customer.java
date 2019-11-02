package com.example.crm.domain.dto.out;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Customer
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer implements Serializable {
    
    /**
     *
     */
    private static final long serialVersionUID = 5318477564853610482L;

    private Long customerId;

    private String nameFirst;

    private String nameMiddle;

    private String nameLast ;

    private String phone;

    private char status;

    private Date createOn;

    private Long version;

    private List<CustomerNote> notes;
}
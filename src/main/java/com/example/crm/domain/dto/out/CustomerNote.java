package com.example.crm.domain.dto.out;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CustomerNote
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerNote implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -5866500234737392174L;
    private Long customerNoteId;
    private String note; 
    private Date createOn;
    private Long version;
    private Long customerId;
}